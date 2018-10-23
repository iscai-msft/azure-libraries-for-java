/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.containerregistry;

import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.utils.SdkContext;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

public class RegistryTaskTests extends RegistryTest {

    @Test
    @Ignore("Needs personal tokens to run")
    public void FileTaskTest(){
        final String acrName = generateRandomResourceName("acr", 10);
        final String rgName = generateRandomResourceName("rgacr", 10);
        String githubRepoUrl = "Replace with your github repository url, eg: https://github.com/Azure/acr.git";
        String githubBranch = "Replace with your github repositoty branch, eg: master";
        String githubPAT = "Replace with your github personal access token which should have the scopes: admin:repo_hook and repo";
        String taskFilePath = "https://github.com/iscai-msft/file_task_test.git#master:samples/java/task/acb.yaml";

        Registry registry = registryManager.containerRegistries().define(acrName)
                .withRegion(Region.US_WEST_CENTRAL)
                .withNewResourceGroup(rgName)
                .withPremiumSku()
                .withRegistryNameAsAdminUser()
                .withTag("tag1", "value1")
                .create();


        SourceTrigger sourceTrigger = new SourceTrigger()
                .withName("SampleSourceTrigger")
                .withSourceRepository(new SourceProperties()
                        .withSourceControlType(SourceControlType.GITHUB)
                        .withBranch(githubBranch)
                        .withRepositoryUrl(githubRepoUrl)
                        .withSourceControlAuthProperties(new AuthInfo().withTokenType(TokenType.PAT).withToken(githubPAT)))
                .withSourceTriggerEvents(Arrays.asList(SourceTriggerEvent.COMMIT, SourceTriggerEvent.PULLREQUEST))
                .withStatus(TriggerStatus.ENABLED);

        BaseImageTrigger baseImageTrigger = new BaseImageTrigger()
                .withName("SampleBaseImageTrigger")
                .withBaseImageTriggerType(BaseImageTriggerType.RUNTIME);


        String taskName = generateRandomResourceName("ft", 10);
        Task task = registryManager.containerRegistryTasks().define(taskName)

                .withExistingRegistry(rgName, acrName)
                .withLocation(Region.US_WEST_CENTRAL.name())
                .withLinux(Architecture.AMD64)
                .defineFileTaskStep()
                    .withTaskPath("https://github.com/iscai-msft/file_task_test.git#master:samples/java/task/acb.yaml")
                    .attach()
                .withCpuCount(2)
                .withTrigger(Arrays.asList(sourceTrigger), baseImageTrigger)
                .create();

        RegistryFileTaskStep registryFileTaskStep = (RegistryFileTaskStep) task.registryTaskStep();

        //Assert the name of the task is correct
        Assert.assertEquals(taskName, task.name());

        //Assert the resource group name is correct
        Assert.assertEquals(rgName, task.resourceGroupName());

        //Assert location is correct
        Assert.assertEquals(Region.US_WEST_CENTRAL.name(), task.regionName());

        //Assert OS is correct
        Assert.assertEquals(OS.LINUX, task.platform().os());

        //Assert architecture is correct
        Assert.assertEquals(Architecture.AMD64, task.platform().architecture());

        //Assert that the task file path is correct
        Assert.assertEquals(taskFilePath, registryFileTaskStep.taskFilePath());

        //Assert CPU count is correct
        Assert.assertEquals(2, task.cpuCount());

        //Assert the length of the source triggers array list is correct
        Assert.assertTrue(task.trigger().sourceTriggers().size() == 1);

        //Assert source triggers are correct
        Assert.assertEquals(sourceTrigger.name(), task.trigger().sourceTriggers().get(0).name());

        //Assert base image trigger is correct
        Assert.assertEquals(baseImageTrigger.name(), task.trigger().baseImageTrigger().name());


    }

    @Test
    @Ignore("Needs personal tokens to run")
    public void FileTaskUpdateTest(){
        final String acrName = generateRandomResourceName("acr", 10);
        final String rgName = generateRandomResourceName("rgacr", 10);
        String githubRepoUrl = "Replace with your github repository url, eg: https://github.com/Azure/acr.git";
        String githubBranch = "Replace with your github repositoty branch, eg: master";
        String githubPAT = "Replace with your github personal access token which should have the scopes: admin:repo_hook and repo";
        String taskFilePath = "https://github.com/iscai-msft/file_task_test.git#master:samples/java/task/acb_update.yaml";
        String taskFileUpdatePath = "https://github.com/iscai-msft/file_task_test.git#master:samples/java/task/acb_update.yaml";

        Registry registry = registryManager.containerRegistries().define(acrName)
                .withRegion(Region.US_WEST_CENTRAL)
                .withNewResourceGroup(rgName)
                .withPremiumSku()
                .withRegistryNameAsAdminUser()
                .withTag("tag1", "value1")
                .create();

        SourceTrigger sourceTrigger = new SourceTrigger()
                .withName("SampleSourceTrigger")
                .withSourceRepository(new SourceProperties()
                        .withSourceControlType(SourceControlType.GITHUB)
                        .withBranch(githubBranch)
                        .withRepositoryUrl(githubRepoUrl)
                        .withSourceControlAuthProperties(new AuthInfo().withTokenType(TokenType.PAT).withToken(githubPAT)))
                .withSourceTriggerEvents(Arrays.asList(SourceTriggerEvent.COMMIT, SourceTriggerEvent.PULLREQUEST))
                .withStatus(TriggerStatus.ENABLED);

        BaseImageTrigger baseImageTrigger = new BaseImageTrigger()
                .withName("SampleBaseImageTrigger")
                .withBaseImageTriggerType(BaseImageTriggerType.RUNTIME);


        String taskName = generateRandomResourceName("ft", 10);
        Task task = registryManager.containerRegistryTasks().define(taskName)

                .withExistingRegistry(rgName, acrName)
                .withLocation(Region.US_WEST_CENTRAL.name())
                .withLinux(Architecture.AMD64)
                .defineFileTaskStep()
                    .withTaskPath(taskFilePath)
                    .attach()
                .withCpuCount(2)
                .withTrigger(Arrays.asList(sourceTrigger), baseImageTrigger)
                .create();

        task.update()
                .updateFileTaskStep()
                    .withTaskPath(taskFileUpdatePath)
                    .parent()
                .apply();

        RegistryFileTaskStep registryFileTaskStep = (RegistryFileTaskStep) task.registryTaskStep();

        //Assert the name of the task is correct
        Assert.assertEquals(taskName, task.name());

        //Assert the resource group name is correct
        Assert.assertEquals(rgName, task.resourceGroupName());

        //Assert location is correct
        Assert.assertEquals(Region.US_WEST_CENTRAL.name(), task.regionName());

        //Assert OS is correct
        Assert.assertEquals(OS.LINUX, task.platform().os());

        //Assert architecture is correct
        Assert.assertEquals(Architecture.AMD64, task.platform().architecture());

        //Assert CPU count is correct
        Assert.assertEquals(2, task.cpuCount());

        //Assert the length of the source triggers array list is correct
        Assert.assertTrue(task.trigger().sourceTriggers().size() == 1);

        //Assert source triggers are correct
        Assert.assertEquals(sourceTrigger.name(), task.trigger().sourceTriggers().get(0).name());

        //Assert base image trigger is correct
        Assert.assertEquals(baseImageTrigger.name(), task.trigger().baseImageTrigger().name());

        //Checking to see whether file path name is updated correctly
        Assert.assertEquals(taskFilePath, registryFileTaskStep.taskFilePath());

        boolean errorRaised = false;
        try {
            task.update()
                    .updateEncodedTaskStep()
                    .parent()
                    .apply();
        } catch (IllegalArgumentException e) {
            errorRaised = true;
        }

        //Checking to see whether error is raised if update is called on the incorrect task step type.
        Assert.assertTrue(errorRaised);
    }

    @Test
    @Ignore("Needs personal tokens to run")
    public void EncodedTaskTest(){

        final String acrName = generateRandomResourceName("acr", 10);
        final String rgName = generateRandomResourceName("rgacr", 10);
        String githubRepoUrl = "Replace with your github repository url, eg: https://github.com/Azure/acr.git";
        String githubBranch = "Replace with your github repositoty branch, eg: master";
        String githubPAT = "Replace with your github personal access token which should have the scopes: admin:repo_hook and repo";
        String encodedTaskContent = "dmVyc2lvbjogMC4wLjEKc3RlcHM6CiAgLSBidWlsZDogLXQge3suUnVuLlJlZ2lzdHJ5fX0vamF2YS1zYW1wbGU6e3suUnVuLklEfX0gLgogICAgd29ya2luZ0RpcmVjdG9yeTogc2FtcGxlcy9qYXZhL3Rhc2sKICAtIHB1c2g6IAogICAgLSB7ey5SdW4uUmVnaXN0cnl9fS9qYXZhLXNhbXBsZTp7ey5SdW4uSUR9fQ==";

        Registry registry = registryManager.containerRegistries().define(acrName)
                .withRegion(Region.US_WEST_CENTRAL)
                .withNewResourceGroup(rgName)
                .withPremiumSku()
                .withRegistryNameAsAdminUser()
                .withTag("tag1", "value1")
                .create();

        SourceTrigger sourceTrigger = new SourceTrigger()
                .withName("SampleSourceTrigger")
                .withSourceRepository(new SourceProperties()
                        .withSourceControlType(SourceControlType.GITHUB)
                        .withBranch(githubBranch)
                        .withRepositoryUrl(githubRepoUrl)
                        .withSourceControlAuthProperties(new AuthInfo().withTokenType(TokenType.PAT).withToken(githubPAT)))
                .withSourceTriggerEvents(Arrays.asList(SourceTriggerEvent.COMMIT, SourceTriggerEvent.PULLREQUEST))
                .withStatus(TriggerStatus.ENABLED);

        BaseImageTrigger baseImageTrigger = new BaseImageTrigger()
                .withName("SampleBaseImageTrigger")
                .withBaseImageTriggerType(BaseImageTriggerType.RUNTIME);


        String taskName = generateRandomResourceName("ft", 10);

        Task task = registryManager.containerRegistryTasks().define(taskName)

                .withExistingRegistry(rgName, acrName)
                .withLocation(Region.US_WEST_CENTRAL.name())
                .withLinux(Architecture.AMD64)
                .defineEncodedTaskStep()
                    .withBase64EncodedTaskContent(encodedTaskContent)
                    .attach()
                .withCpuCount(2)
                .withTrigger(Arrays.asList(sourceTrigger), baseImageTrigger)
                .create();

        RegistryEncodedTaskStep registryEncodedTaskStep = (RegistryEncodedTaskStep) task.registryTaskStep();

        //Assert the name of the task is correct
        Assert.assertEquals(taskName, task.name());

        //Assert the resource group name is correct
        Assert.assertEquals(rgName, task.resourceGroupName());

        //Assert location is correct
        Assert.assertEquals(Region.US_WEST_CENTRAL.name(), task.regionName());

        //Assert OS is correct
        Assert.assertEquals(OS.LINUX, task.platform().os());

        //Assert architecture is correct
        Assert.assertEquals(Architecture.AMD64, task.platform().architecture());

        //Assert that the task file path is correct
        Assert.assertEquals(encodedTaskContent, registryEncodedTaskStep.encodedTaskContent());

        //Assert CPU count is correct
        Assert.assertEquals(2, task.cpuCount());

        //Assert the length of the source triggers array list is correct
        Assert.assertTrue(task.trigger().sourceTriggers().size() == 1);

        //Assert source triggers are correct
        Assert.assertEquals(sourceTrigger.name(), task.trigger().sourceTriggers().get(0).name());

        //Assert base image trigger is correct
        Assert.assertEquals(baseImageTrigger.name(), task.trigger().baseImageTrigger().name());

    }


    @Test
    @Ignore("Needs personal tokens to run")
    public void EncodedTaskUpdateTest(){

        final String acrName = generateRandomResourceName("acr", 10);
        final String rgName = generateRandomResourceName("rgacr", 10);
        String githubRepoUrl = "Replace with your github repository url, eg: https://github.com/Azure/acr.git";
        String githubBranch = "Replace with your github repositoty branch, eg: master";
        String githubPAT = "Replace with your github personal access token which should have the scopes: admin:repo_hook and repo";
        String encodedTaskContent = "dmVyc2lvbjogMC4wLjEKc3RlcHM6CiAgLSBidWlsZDogLXQge3suUnVuLlJlZ2lzdHJ5fX0vamF2YS1zYW1wbGU6e3suUnVuLklEfX0gLgogICAgd29ya2luZ0RpcmVjdG9yeTogc2FtcGxlcy9qYXZhL3Rhc2sKICAtIHB1c2g6IAogICAgLSB7ey5SdW4uUmVnaXN0cnl9fS9qYXZhLXNhbXBsZTp7ey5SdW4uSUR9fQ==";
        String encodedTaskContentUpdate = "dmVyc2lvbjogMC4wLjEKc3RlcHM6CiAgLSBidWlsZDogLXQge3suUnVuLlJlZ2lzdHJ5fX0vamF2YS1zYW1wbGU6e3suUnVuLklEfX0gLgogICAgd29ya2luZ0RpcmVjdG9yeTogc2FtcGxlcy9qYXZhL3VwZGF0ZQogIC0gcHVzaDogCiAgICAtIHt7LlJ1bi5SZWdpc3RyeX19L2phdmEtc2FtcGxlOnt7LlJ1bi5JRH19";

        Registry registry = registryManager.containerRegistries().define(acrName)
                .withRegion(Region.US_WEST_CENTRAL)
                .withNewResourceGroup(rgName)
                .withPremiumSku()
                .withRegistryNameAsAdminUser()
                .withTag("tag1", "value1")
                .create();

        SourceTrigger sourceTrigger = new SourceTrigger()
                .withName("SampleSourceTrigger")
                .withSourceRepository(new SourceProperties()
                        .withSourceControlType(SourceControlType.GITHUB)
                        .withBranch(githubBranch)
                        .withRepositoryUrl(githubRepoUrl)
                        .withSourceControlAuthProperties(new AuthInfo().withTokenType(TokenType.PAT).withToken(githubPAT)))
                .withSourceTriggerEvents(Arrays.asList(SourceTriggerEvent.COMMIT, SourceTriggerEvent.PULLREQUEST))
                .withStatus(TriggerStatus.ENABLED);

        BaseImageTrigger baseImageTrigger = new BaseImageTrigger()
                .withName("SampleBaseImageTrigger")
                .withBaseImageTriggerType(BaseImageTriggerType.RUNTIME);


        String taskName = generateRandomResourceName("ft", 10);

        Task task = registryManager.containerRegistryTasks().define(taskName)

                .withExistingRegistry(rgName, acrName)
                .withLocation(Region.US_WEST_CENTRAL.name())
                .withLinux(Architecture.AMD64)
                .defineEncodedTaskStep()
                .withBase64EncodedTaskContent(encodedTaskContent)
                .attach()
                .withCpuCount(2)
                .withTrigger(Arrays.asList(sourceTrigger), baseImageTrigger)
                .create();


        task.update()
                .updateEncodedTaskStep()
                    .withBase64EncodedTaskContent(encodedTaskContentUpdate)
                    .parent()
                .withCpuCount(1)
                .apply();

        RegistryEncodedTaskStep registryEncodedTaskStep = (RegistryEncodedTaskStep) task.registryTaskStep();

        //Assert the name of the task is correct
        Assert.assertEquals(taskName, task.name());

        //Assert the resource group name is correct
        Assert.assertEquals(rgName, task.resourceGroupName());

        //Assert location is correct
        Assert.assertEquals(Region.US_WEST_CENTRAL.name(), task.regionName());

        //Assert OS is correct
        Assert.assertEquals(OS.LINUX, task.platform().os());

        //Assert architecture is correct
        Assert.assertEquals(Architecture.AMD64, task.platform().architecture());

        //Assert that the task file path is correct
        Assert.assertEquals(encodedTaskContentUpdate, registryEncodedTaskStep.encodedTaskContent());

        //Assert CPU count is correct
        Assert.assertEquals(1, task.cpuCount());

        //Assert the length of the source triggers array list is correct
        Assert.assertTrue(task.trigger().sourceTriggers().size() == 1);

        //Assert source triggers are correct
        Assert.assertEquals(sourceTrigger.name(), task.trigger().sourceTriggers().get(0).name());

        //Assert base image trigger is correct
        Assert.assertEquals(baseImageTrigger.name(), task.trigger().baseImageTrigger().name());

        boolean errorRaised = false;
        try {
            task.update()
                    .updateDockerTaskStep()
                    .parent()
                    .apply();
        } catch (IllegalArgumentException e) {
            errorRaised = true;
        }

        //Checking to see whether error is raised if update is called on the incorrect task step type.
        Assert.assertTrue(errorRaised);

    }

    @Test
    @Ignore("Needs personal tokens to run")
    public void DockerTaskTest(){
        final String acrName = generateRandomResourceName("acr", 10);
        final String rgName = generateRandomResourceName("rgacr", 10);
        String githubRepoUrl = "Replace with your github repository url, eg: https://github.com/Azure/acr.git";
        String githubBranch = "Replace with your github repositoty branch, eg: master";
        String githubPAT = "Replace with your github personal access token which should have the scopes: admin:repo_hook and repo";
        String dockerFilePath = "Replace with your docker file path relative to githubContext, eg: Dockerfile";
        String imageName = "java-sample:{{.Run.ID}}";

        Registry registry = registryManager.containerRegistries().define(acrName)
                .withRegion(Region.US_WEST_CENTRAL)
                .withNewResourceGroup(rgName)
                .withPremiumSku()
                .withRegistryNameAsAdminUser()
                .withTag("tag1", "value1")
                .create();


        SourceTrigger sourceTrigger = new SourceTrigger()
                .withName("SampleSourceTrigger")
                .withSourceRepository(new SourceProperties()
                        .withSourceControlType(SourceControlType.GITHUB)
                        .withBranch(githubBranch)
                        .withRepositoryUrl(githubRepoUrl)
                        .withSourceControlAuthProperties(new AuthInfo().withTokenType(TokenType.PAT).withToken(githubPAT)))
                .withSourceTriggerEvents(Arrays.asList(SourceTriggerEvent.COMMIT, SourceTriggerEvent.PULLREQUEST))
                .withStatus(TriggerStatus.ENABLED);

        BaseImageTrigger baseImageTrigger = new BaseImageTrigger()
                .withName("SampleBaseImageTrigger")
                .withBaseImageTriggerType(BaseImageTriggerType.RUNTIME);


        String taskName = generateRandomResourceName("ft", 10);
        Task task = registryManager.containerRegistryTasks().define(taskName)

                .withExistingRegistry(rgName, acrName)
                .withLocation(Region.US_WEST_CENTRAL.name())
                .withLinux(Architecture.AMD64)
                .defineDockerTaskStep()
                    .withDockerFilePath(dockerFilePath)
                    .withImageNames(Arrays.asList(imageName))
                    .withCache()
                    .withPushEnabled()
                    .attach()
                .withCpuCount(2)
                .withTrigger(Arrays.asList(sourceTrigger), baseImageTrigger)
                .create();

        RegistryDockerTaskStep registryDockerTaskStep = (RegistryDockerTaskStep) task.registryTaskStep();

        //Assert the name of the task is correct
        Assert.assertEquals(taskName, task.name());

        //Assert the resource group name is correct
        Assert.assertEquals(rgName, task.resourceGroupName());

        //Assert location is correct
        Assert.assertEquals(Region.US_WEST_CENTRAL.name(), task.regionName());

        //Assert OS is correct
        Assert.assertEquals(OS.LINUX, task.platform().os());

        //Assert architecture is correct
        Assert.assertEquals(Architecture.AMD64, task.platform().architecture());

        //Assert that the task file path is correct
        Assert.assertEquals(dockerFilePath, registryDockerTaskStep.dockerFilePath());

        //Assert that the image name array is correct
        Assert.assertEquals(imageName, registryDockerTaskStep.imageNames().get(0));

        //Assert that with cache works
        Assert.assertTrue(!registryDockerTaskStep.noCache());

        //Assert that push is enabled
        Assert.assertTrue(registryDockerTaskStep.isPushEnabled());

        //Assert CPU count is correct
        Assert.assertEquals(2, task.cpuCount());

        //Assert the length of the source triggers array list is correct
        Assert.assertTrue(task.trigger().sourceTriggers().size() == 1);

        //Assert source triggers are correct
        Assert.assertEquals(sourceTrigger.name(), task.trigger().sourceTriggers().get(0).name());

        //Assert base image trigger is correct
        Assert.assertEquals(baseImageTrigger.name(), task.trigger().baseImageTrigger().name());

    }

    @Test
    @Ignore("Needs personal tokens to run")
    public void DockerTaskUpdateTest(){
        final String acrName = generateRandomResourceName("acr", 10);
        final String rgName = generateRandomResourceName("rgacr", 10);
        String githubRepoUrl = "Replace with your github repository url, eg: https://github.com/Azure/acr.git";
        String githubBranch = "Replace with your github repositoty branch, eg: master";
        String githubPAT = "Replace with your github personal access token which should have the scopes: admin:repo_hook and repo";
        String dockerFilePath = "Replace with your docker file path relative to githubContext, eg: Dockerfile";
        String dockerFilePathUpdate = "Replace this with your docker file path that you updated your task to, if you did update your docker file path";
        String imageName = "java-sample:{{.Run.ID}}";

        Registry registry = registryManager.containerRegistries().define(acrName)
                .withRegion(Region.US_WEST_CENTRAL)
                .withNewResourceGroup(rgName)
                .withPremiumSku()
                .withRegistryNameAsAdminUser()
                .withTag("tag1", "value1")
                .create();


        SourceTrigger sourceTrigger = new SourceTrigger()
                .withName("SampleSourceTrigger")
                .withSourceRepository(new SourceProperties()
                        .withSourceControlType(SourceControlType.GITHUB)
                        .withBranch(githubBranch)
                        .withRepositoryUrl(githubRepoUrl)
                        .withSourceControlAuthProperties(new AuthInfo().withTokenType(TokenType.PAT).withToken(githubPAT)))
                .withSourceTriggerEvents(Arrays.asList(SourceTriggerEvent.COMMIT, SourceTriggerEvent.PULLREQUEST))
                .withStatus(TriggerStatus.ENABLED);

        BaseImageTrigger baseImageTrigger = new BaseImageTrigger()
                .withName("SampleBaseImageTrigger")
                .withBaseImageTriggerType(BaseImageTriggerType.RUNTIME);


        String taskName = generateRandomResourceName("ft", 10);
        Task task = registryManager.containerRegistryTasks().define(taskName)

                .withExistingRegistry(rgName, acrName)
                .withLocation(Region.US_WEST_CENTRAL.name())
                .withLinux(Architecture.AMD64)
                .defineDockerTaskStep()
                .withDockerFilePath(dockerFilePath)
                .withImageNames(Arrays.asList(imageName))
                .withCache()
                .withPushEnabled()
                .attach()
                .withCpuCount(2)
                .withTrigger(Arrays.asList(sourceTrigger), baseImageTrigger)
                .create();

        task.update()
                .updateDockerTaskStep()
                    .withDockerFilePath(dockerFilePathUpdate)
                    .withoutCache()
                    .withPushDisabled()
                    .parent()
                .withCpuCount(1)
                .apply();

        RegistryDockerTaskStep registryDockerTaskStep = (RegistryDockerTaskStep) task.registryTaskStep();

        //Assert the name of the task is correct
        Assert.assertEquals(taskName, task.name());

        //Assert the resource group name is correct
        Assert.assertEquals(rgName, task.resourceGroupName());

        //Assert location is correct
        Assert.assertEquals(Region.US_WEST_CENTRAL.name(), task.regionName());

        //Assert OS is correct
        Assert.assertEquals(OS.LINUX, task.platform().os());

        //Assert architecture is correct
        Assert.assertEquals(Architecture.AMD64, task.platform().architecture());

        //Assert that the task file path is correct
        Assert.assertEquals(dockerFilePathUpdate, registryDockerTaskStep.dockerFilePath());

        //Assert that the image name array is correct
        Assert.assertEquals(imageName, registryDockerTaskStep.imageNames().get(0));

        //Assert that with no cache works
        Assert.assertTrue(registryDockerTaskStep.noCache());

        //Assert that push is disabled
        Assert.assertTrue(!registryDockerTaskStep.isPushEnabled());

        //Assert CPU count is correct
        Assert.assertEquals(1, task.cpuCount());

        //Assert the length of the source triggers array list is correct
        Assert.assertTrue(task.trigger().sourceTriggers().size() == 1);

        //Assert source triggers are correct
        Assert.assertEquals(sourceTrigger.name(), task.trigger().sourceTriggers().get(0).name());

        //Assert base image trigger is correct
        Assert.assertEquals(baseImageTrigger.name(), task.trigger().baseImageTrigger().name());

        boolean errorRaised = false;
        try {
            task.update()
                    .updateFileTaskStep()
                    .parent()
                    .apply();
        } catch (IllegalArgumentException e) {
            errorRaised = true;
        }

        //Checking to see whether error is raised if update is called on the incorrect task step type.
        Assert.assertTrue(errorRaised);

    }




    @Override
    protected void cleanUpResources() {
    }
}