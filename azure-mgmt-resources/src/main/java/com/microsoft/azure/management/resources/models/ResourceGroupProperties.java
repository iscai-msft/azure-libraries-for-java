/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator 0.15.0.0
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.management.resources.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The resource group properties.
 */
public class ResourceGroupProperties {
    /**
     * Gets resource group provisioning state.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String provisioningState;

    /**
     * Get the provisioningState value.
     *
     * @return the provisioningState value
     */
    public String getProvisioningState() {
        return this.provisioningState;
    }

}
