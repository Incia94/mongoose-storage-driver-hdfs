package com.emc.mongoose.storage.driver.hdfs;

import com.emc.mongoose.data.DataInput;
import com.emc.mongoose.env.ExtensionBase;
import com.emc.mongoose.exception.OmgShootMyFootException;
import com.emc.mongoose.item.Item;
import com.emc.mongoose.item.io.task.IoTask;
import com.emc.mongoose.storage.driver.StorageDriverFactory;

import com.github.akurilov.confuse.Config;
import com.github.akurilov.confuse.SchemaProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class HdfsStorageDriverExtension<
	I extends Item, O extends IoTask<I>, T extends HdfsStorageDriver<I, O>
>
extends ExtensionBase
implements StorageDriverFactory<I, O, T> {

	private static final String NAME = "hdfs";
	private static final List<String> RES_INSTALL_FILES = Collections.unmodifiableList(
		Arrays.asList(
		)
	);

	@Override
	public final String id() {
		return NAME;
	}

	@Override @SuppressWarnings("unchecked")
	public final T create(
		final String stepId, final DataInput dataInput, final Config loadConfig,
		final Config storageConfig, final boolean verifyFlag
	) throws OmgShootMyFootException, InterruptedException {
		return (T) new HdfsStorageDriver<I, O>(
			NAME, stepId, dataInput, loadConfig, storageConfig, verifyFlag
		);
	}

	@Override
	protected final String defaultsFileName() {
		return null;
	}

	@Override
	public final SchemaProvider schemaProvider() {
		return null;
	}

	@Override
	protected final List<String> resourceFilesToInstall() {
		return RES_INSTALL_FILES;
	}
}
