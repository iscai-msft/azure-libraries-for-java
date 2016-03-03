/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator 0.15.0.0
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.management.website.models;


/**
 * The NetworkAccessControlEntry model.
 */
public class NetworkAccessControlEntry {
    /**
     * Possible values include: 'Permit', 'Deny'.
     */
    private AccessControlEntryAction action;

    /**
     * The description property.
     */
    private String description;

    /**
     * The order property.
     */
    private Integer order;

    /**
     * The remoteSubnet property.
     */
    private String remoteSubnet;

    /**
     * Get the action value.
     *
     * @return the action value
     */
    public AccessControlEntryAction getAction() {
        return this.action;
    }

    /**
     * Set the action value.
     *
     * @param action the action value to set
     */
    public void setAction(AccessControlEntryAction action) {
        this.action = action;
    }

    /**
     * Get the description value.
     *
     * @return the description value
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description value.
     *
     * @param description the description value to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the order value.
     *
     * @return the order value
     */
    public Integer getOrder() {
        return this.order;
    }

    /**
     * Set the order value.
     *
     * @param order the order value to set
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * Get the remoteSubnet value.
     *
     * @return the remoteSubnet value
     */
    public String getRemoteSubnet() {
        return this.remoteSubnet;
    }

    /**
     * Set the remoteSubnet value.
     *
     * @param remoteSubnet the remoteSubnet value to set
     */
    public void setRemoteSubnet(String remoteSubnet) {
        this.remoteSubnet = remoteSubnet;
    }

}
