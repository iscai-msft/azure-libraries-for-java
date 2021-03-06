/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for OptimizationType.
 */
public final class OptimizationType extends ExpandableStringEnum<OptimizationType> {
    /** Static value GeneralWebDelivery for OptimizationType. */
    public static final OptimizationType GENERAL_WEB_DELIVERY = fromString("GeneralWebDelivery");

    /** Static value GeneralMediaStreaming for OptimizationType. */
    public static final OptimizationType GENERAL_MEDIA_STREAMING = fromString("GeneralMediaStreaming");

    /** Static value VideoOnDemandMediaStreaming for OptimizationType. */
    public static final OptimizationType VIDEO_ON_DEMAND_MEDIA_STREAMING = fromString("VideoOnDemandMediaStreaming");

    /** Static value LargeFileDownload for OptimizationType. */
    public static final OptimizationType LARGE_FILE_DOWNLOAD = fromString("LargeFileDownload");

    /** Static value DynamicSiteAcceleration for OptimizationType. */
    public static final OptimizationType DYNAMIC_SITE_ACCELERATION = fromString("DynamicSiteAcceleration");

    /**
     * Creates or finds a OptimizationType from its string representation.
     * @param name a name to look for
     * @return the corresponding OptimizationType
     */
    @JsonCreator
    public static OptimizationType fromString(String name) {
        return fromString(name, OptimizationType.class);
    }

    /**
     * @return known OptimizationType values
     */
    public static Collection<OptimizationType> values() {
        return values(OptimizationType.class);
    }
}
