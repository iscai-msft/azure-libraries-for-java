/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.storage.implementation;

import com.microsoft.azure.management.resources.fluentcore.model.implementation.CreatableUpdatableImpl;
import com.microsoft.azure.management.storage.BlobServiceProperties;
import com.microsoft.azure.management.storage.CorsRules;
import com.microsoft.azure.management.storage.DeleteRetentionPolicy;
import retrofit2.http.DELETE;
import rx.Observable;

class BlobServicePropertiesImpl extends CreatableUpdatableImpl<BlobServiceProperties, BlobServicePropertiesInner, BlobServicePropertiesImpl> implements BlobServiceProperties, BlobServiceProperties.Definition, BlobServiceProperties.Update {
    private final StorageManager manager;
    private String resourceGroupName;
    private String accountName;

    BlobServicePropertiesImpl(String name, StorageManager manager) {
        super(name, new BlobServicePropertiesInner());
        this.manager = manager;
        // Set resource name
        this.accountName = name;
        //
    }

    BlobServicePropertiesImpl(BlobServicePropertiesInner inner, StorageManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.accountName = inner.name();
        // set resource ancestor and positional variables
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.accountName = IdParsingUtils.getValueFromIdByName(inner.id(), "storageAccounts");
        //
    }

    @Override
    public StorageManager manager() {
        return this.manager;
    }

    @Override
    public Observable<BlobServiceProperties> createResourceAsync() {
        BlobServicesInner client = this.manager().inner().blobServices();
        return client.setServicePropertiesAsync(this.resourceGroupName, this.accountName, this.inner())
                .map(innerToFluentMap(this));
    }

    @Override
    public Observable<BlobServiceProperties> updateResourceAsync() {
        BlobServicesInner client = this.manager().inner().blobServices();
        return client.setServicePropertiesAsync(this.resourceGroupName, this.accountName, this.inner())
                .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<BlobServicePropertiesInner> getInnerAsync() {
        BlobServicesInner client = this.manager().inner().blobServices();
        return client.getServicePropertiesAsync(this.resourceGroupName, this.accountName);
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }


    @Override
    public CorsRules cors() {
        return this.inner().cors();
    }

    @Override
    public String defaultServiceVersion() {
        return this.inner().defaultServiceVersion();
    }

    @Override
    public DeleteRetentionPolicy deleteRetentionPolicy() {
        return this.inner().deleteRetentionPolicy();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public BlobServicePropertiesImpl withExistingStorageAccount(String resourceGroupName, String accountName) {
        this.resourceGroupName = resourceGroupName;
        this.accountName = accountName;
        return this;
    }

    @Override
    public BlobServicePropertiesImpl withCors(CorsRules cors) {
        this.inner().withCors(cors);
        return this;
    }

    @Override
    public BlobServicePropertiesImpl withDefaultServiceVersion(String defaultServiceVersion) {
        this.inner().withDefaultServiceVersion(defaultServiceVersion);
        return this;
    }

    @Override
    public BlobServicePropertiesImpl withDeleteRetentionPolicy(DeleteRetentionPolicy deleteRetentionPolicy) {
        this.inner().withDeleteRetentionPolicy(deleteRetentionPolicy);
        return this;
    }

    @Override
    public BlobServicePropertiesImpl withDeleteRetentionPolicyEnabled(int numDaysEnabled) {
        this.inner().withDeleteRetentionPolicy(new DeleteRetentionPolicy().withEnabled(true).withDays(numDaysEnabled));
        return this;
    }

    @Override
    public BlobServicePropertiesImpl withDeleteRetentionPolicyDisabled() {
        this.inner().withDeleteRetentionPolicy(new DeleteRetentionPolicy().withEnabled(false));
        return this;
    }

}