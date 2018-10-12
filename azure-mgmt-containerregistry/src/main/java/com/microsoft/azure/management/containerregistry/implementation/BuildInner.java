/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.containerregistry.implementation;

import com.microsoft.azure.management.containerregistry.BuildStatus;
import org.joda.time.DateTime;
import com.microsoft.azure.management.containerregistry.BuildType;
import java.util.List;
import com.microsoft.azure.management.containerregistry.ImageDescriptor;
import com.microsoft.azure.management.containerregistry.ImageUpdateTrigger;
import com.microsoft.azure.management.containerregistry.GitCommitTrigger;
import com.microsoft.azure.management.containerregistry.PlatformProperties;
import com.microsoft.azure.management.containerregistry.ProvisioningState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.management.containerregistry.ProxyResource;

/**
 * Build resource properties.
 */
@JsonFlatten
public class BuildInner extends ProxyResource {
    /**
     * The unique identifier for the build.
     */
    @JsonProperty(value = "properties.buildId")
    private String buildId;

    /**
     * The current status of the build. Possible values include: 'Queued',
     * 'Started', 'Running', 'Succeeded', 'Failed', 'Canceled', 'Error',
     * 'Timeout'.
     */
    @JsonProperty(value = "properties.status")
    private BuildStatus status;

    /**
     * The last updated time for the build.
     */
    @JsonProperty(value = "properties.lastUpdatedTime")
    private DateTime lastUpdatedTime;

    /**
     * The type of build. Possible values include: 'AutoBuild', 'QuickBuild'.
     */
    @JsonProperty(value = "properties.buildType")
    private BuildType buildType;

    /**
     * The time the build was created.
     */
    @JsonProperty(value = "properties.createTime")
    private DateTime createTime;

    /**
     * The time the build started.
     */
    @JsonProperty(value = "properties.startTime")
    private DateTime startTime;

    /**
     * The time the build finished.
     */
    @JsonProperty(value = "properties.finishTime")
    private DateTime finishTime;

    /**
     * The list of all images that were generated from the build.
     */
    @JsonProperty(value = "properties.outputImages")
    private List<ImageDescriptor> outputImages;

    /**
     * The build task with which the build was started.
     */
    @JsonProperty(value = "properties.buildTask")
    private String buildTask;

    /**
     * The image update trigger that caused the build.
     */
    @JsonProperty(value = "properties.imageUpdateTrigger")
    private ImageUpdateTrigger imageUpdateTrigger;

    /**
     * The git commit trigger that caused the build.
     */
    @JsonProperty(value = "properties.gitCommitTrigger")
    private GitCommitTrigger gitCommitTrigger;

    /**
     * The value that indicates whether archiving is enabled or not.
     */
    @JsonProperty(value = "properties.isArchiveEnabled")
    private Boolean isArchiveEnabled;

    /**
     * The platform properties against which the build will happen.
     */
    @JsonProperty(value = "properties.platform")
    private PlatformProperties platform;

    /**
     * The provisioning state of a build. Possible values include: 'Creating',
     * 'Updating', 'Deleting', 'Succeeded', 'Failed', 'Canceled'.
     */
    @JsonProperty(value = "properties.provisioningState")
    private ProvisioningState provisioningState;

    /**
     * Get the buildId value.
     *
     * @return the buildId value
     */
    public String buildId() {
        return this.buildId;
    }

    /**
     * Set the buildId value.
     *
     * @param buildId the buildId value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withBuildId(String buildId) {
        this.buildId = buildId;
        return this;
    }

    /**
     * Get the status value.
     *
     * @return the status value
     */
    public BuildStatus status() {
        return this.status;
    }

    /**
     * Set the status value.
     *
     * @param status the status value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withStatus(BuildStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the lastUpdatedTime value.
     *
     * @return the lastUpdatedTime value
     */
    public DateTime lastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    /**
     * Set the lastUpdatedTime value.
     *
     * @param lastUpdatedTime the lastUpdatedTime value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withLastUpdatedTime(DateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
        return this;
    }

    /**
     * Get the buildType value.
     *
     * @return the buildType value
     */
    public BuildType buildType() {
        return this.buildType;
    }

    /**
     * Set the buildType value.
     *
     * @param buildType the buildType value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withBuildType(BuildType buildType) {
        this.buildType = buildType;
        return this;
    }

    /**
     * Get the createTime value.
     *
     * @return the createTime value
     */
    public DateTime createTime() {
        return this.createTime;
    }

    /**
     * Set the createTime value.
     *
     * @param createTime the createTime value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withCreateTime(DateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * Get the startTime value.
     *
     * @return the startTime value
     */
    public DateTime startTime() {
        return this.startTime;
    }

    /**
     * Set the startTime value.
     *
     * @param startTime the startTime value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withStartTime(DateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Get the finishTime value.
     *
     * @return the finishTime value
     */
    public DateTime finishTime() {
        return this.finishTime;
    }

    /**
     * Set the finishTime value.
     *
     * @param finishTime the finishTime value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withFinishTime(DateTime finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    /**
     * Get the outputImages value.
     *
     * @return the outputImages value
     */
    public List<ImageDescriptor> outputImages() {
        return this.outputImages;
    }

    /**
     * Set the outputImages value.
     *
     * @param outputImages the outputImages value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withOutputImages(List<ImageDescriptor> outputImages) {
        this.outputImages = outputImages;
        return this;
    }

    /**
     * Get the buildTask value.
     *
     * @return the buildTask value
     */
    public String buildTask() {
        return this.buildTask;
    }

    /**
     * Set the buildTask value.
     *
     * @param buildTask the buildTask value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withBuildTask(String buildTask) {
        this.buildTask = buildTask;
        return this;
    }

    /**
     * Get the imageUpdateTrigger value.
     *
     * @return the imageUpdateTrigger value
     */
    public ImageUpdateTrigger imageUpdateTrigger() {
        return this.imageUpdateTrigger;
    }

    /**
     * Set the imageUpdateTrigger value.
     *
     * @param imageUpdateTrigger the imageUpdateTrigger value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withImageUpdateTrigger(ImageUpdateTrigger imageUpdateTrigger) {
        this.imageUpdateTrigger = imageUpdateTrigger;
        return this;
    }

    /**
     * Get the gitCommitTrigger value.
     *
     * @return the gitCommitTrigger value
     */
    public GitCommitTrigger gitCommitTrigger() {
        return this.gitCommitTrigger;
    }

    /**
     * Set the gitCommitTrigger value.
     *
     * @param gitCommitTrigger the gitCommitTrigger value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withGitCommitTrigger(GitCommitTrigger gitCommitTrigger) {
        this.gitCommitTrigger = gitCommitTrigger;
        return this;
    }

    /**
     * Get the isArchiveEnabled value.
     *
     * @return the isArchiveEnabled value
     */
    public Boolean isArchiveEnabled() {
        return this.isArchiveEnabled;
    }

    /**
     * Set the isArchiveEnabled value.
     *
     * @param isArchiveEnabled the isArchiveEnabled value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withIsArchiveEnabled(Boolean isArchiveEnabled) {
        this.isArchiveEnabled = isArchiveEnabled;
        return this;
    }

    /**
     * Get the platform value.
     *
     * @return the platform value
     */
    public PlatformProperties platform() {
        return this.platform;
    }

    /**
     * Set the platform value.
     *
     * @param platform the platform value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withPlatform(PlatformProperties platform) {
        this.platform = platform;
        return this;
    }

    /**
     * Get the provisioningState value.
     *
     * @return the provisioningState value
     */
    public ProvisioningState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioningState value.
     *
     * @param provisioningState the provisioningState value to set
     * @return the BuildInner object itself.
     */
    public BuildInner withProvisioningState(ProvisioningState provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

}
