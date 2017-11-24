package com.emc.mongoose.storage.driver.hdfs;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.PullImageResultCallback;

import org.junit.rules.ExternalResource;

public class HdfsNodeContainerResource
extends ExternalResource {

	protected static final String IMAGE_NAME = "dockerq/docker-hdfs";
	protected static final int PORT = 9000;
	private static final DockerClient DOCKER_CLIENT = DockerClientBuilder.getInstance().build();

	private String containerId = null;

	@Override
	protected void before()
	throws Throwable {
		System.out.println("docker pull " + IMAGE_NAME + "...");
		DOCKER_CLIENT
			.pullImageCmd(IMAGE_NAME)
			.exec(new PullImageResultCallback())
			.awaitCompletion();
		final CreateContainerResponse container = DOCKER_CLIENT
			.createContainerCmd(IMAGE_NAME)
			.withName("hdfs_node")
			.withNetworkMode("host")
			.exec();
		containerId = container.getId();
		DOCKER_CLIENT.startContainerCmd(containerId).exec();

	}

	@Override
	protected void after() {
		if(containerId != null) {
			DOCKER_CLIENT.killContainerCmd(containerId).exec();
			DOCKER_CLIENT.removeContainerCmd(containerId).exec();
			containerId = null;
		}
	}
}
