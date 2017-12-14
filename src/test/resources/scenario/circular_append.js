Command
	.value("rm -f " + ITEM_LIST_FILE_0 + " " + ITEM_LIST_FILE_1)
	.run();

PreconditionLoad
	.config(
		{
			"item": {
				"data" : {
					"size": ITEM_DATA_SIZE
				},
				"output": {
					"file": ITEM_LIST_FILE_0
				}
			},
			"test": {
				"step": {
					"limit": {
						"count": BASE_ITEMS_COUNT
					}
				}
			}
		}
	)
	.run();

Load
	.config(
		{
			"item": {
				"data": {
					"ranges": {
						"fixed": "-" + ITEM_DATA_SIZE + "-"
					}
				},
				"input": {
					"file": ITEM_LIST_FILE_0
				},
				"output": {
					"file": ITEM_LIST_FILE_1
				}
			},
			"load": {
				"type": "update",
				"generator": {
					"recycle": {
						"enabled": true
					}
				}
			},
			"test": {
				"step": {
					"limit": {
						"count": ~~(BASE_ITEMS_COUNT * APPEND_COUNT)
					}
				}
			}
		}
	)
	.run();
