/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.containerregistry;


import java.util.List;
import java.util.Map;
import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.management.resources.fluentcore.model.Attachable;
import com.microsoft.azure.management.resources.fluentcore.model.Settable;

/**
 * The properties of an encoded task step.
 */
@Fluent()
@Beta(Beta.SinceVersion.V1_1_0)
public interface RegistryEncodedTaskStep extends RegistryTaskStep {

    /**
     * @return the encoded task content of this encoded task step
     */
    String encodedTaskContent();

    /**
     * @return the encoded values content of this encoded task step
     */
    String encodedValuesContent();

    /**
     * @return the values of this encoded task step
     */
    List<SetValue> values();

    /**
     * Container interface for all the definitions related to a RegistryEncodedTaskStep.
     */
    interface Definition extends
            RegistryEncodedTaskStep.DefinitionStages.Blank,
            RegistryEncodedTaskStep.DefinitionStages.EncodedTaskStep,
            RegistryEncodedTaskStep.DefinitionStages.EncodedTaskStepAttachable {


    }

    /**
     * Container interface for all the updates related to a RegistryEncodedTaskStep.
     */
    interface Update extends
            RegistryEncodedTaskStep.UpdateStages.EncodedTaskStep,
            RegistryEncodedTaskStep.UpdateStages.ValuePath,
            RegistryEncodedTaskStep.UpdateStages.OverridingValues,
            Settable<Task.Update> {

    }

    /**
     * Grouping of registry task definition stages.
     */
    interface DefinitionStages {

        /**
         * The first stage of a RegistryEncodedTaskStep definition.
         */
        interface Blank extends EncodedTaskStep {

        }

        /**
         * The stage of the container registry EncodedTaskStep definition allowing to specify the base 64 encoded task content.
         */
        interface EncodedTaskStep {

            /**
             * The function that specifies the base64 encoded task content.
             *
             * @param encodedTaskContent the base64 encoded task content.
             * @return the next stage of the container registry EncodedTaskStep definition.
             */
            EncodedTaskStepAttachable withBase64EncodedTaskContent(String encodedTaskContent);
        }


        /**
         * The stage of the definition which contains all the minimum required inputs for the resource to be attached,
         *  but also allows for any other optional settings to be specified.
         */
        interface EncodedTaskStepAttachable extends Attachable<Task.DefinitionStages.TaskCreatable> {

            /**
             * The function that specifies the base64 encoded value content.
             *
             * @param encodedValueContent the base64 encoded value content.
             * @return the next stage of the container registry EncodedTaskStep definition.
             */
            EncodedTaskStepAttachable withBase64EncodedValueContent(String encodedValueContent);

            /**
             * The function that specifies the values that override the corresponding values specified under the function withBase64EncodedValueContent().
             *
             * @param overridingValues a map which contains the values that will override the corresponding values specified under the function withBase64EncodedValueContent().
             * @return the next stage of the container registry EncodedTaskStep definition.
             */
            EncodedTaskStepAttachable withOverridingValues(Map<String, OverridingValue> overridingValues);

            /**
             * The function that specifies a single value that will override the corresponding value specified under the function withBase64EncodedValueContent().
             * @param name the name of the value to be overriden.
             * @param overridingValue the value of the value to be overriden.
             * @return the next stage of the container registry EncodedTaskStep definition.
             */
            EncodedTaskStepAttachable withOverridingValue(String name, OverridingValue overridingValue);

        }
    }

    /**
     * Grouping of registry task update stages.
     */
    interface UpdateStages {

        /**
         * The stage of the container registry EncodedTaskStep update allowing to specify the task path.
         */
        interface EncodedTaskStep {

            /**
             * The function that specifies the path to the base64 encoded task content.
             *
             * @param encodedTaskContent the path to the base64 encoded task content.
             * @return the next stage of the container registry EncodedTaskStep update.
             */
            Update withBase64EncodedTaskContent(String encodedTaskContent);
        }

        /**
         * The stage of the container registry EncodedTaskStep update allowing to specify the path to the values.
         */
        interface ValuePath {
            /**
             * The function that specifies the path to the base64 encoded value content.
             *
             * @param encodedValueContent the path to the base64 encoded value content.
             * @return the next stage of the container registry EncodedTaskStep update.
             */
            Update withBase64EncodedValueContent(String encodedValueContent);
        }

        /**
         * The stage of the container registry EncodedTaskStep update allowing to specify the overriding values.
         */
        interface OverridingValues {
            /**
             * The function that specifies the values that override the corresponding values specified under the function withBase64EncodedValueContent().
             *
             * @param overridingValues a map which contains the values that will override the corresponding values specified under the function withBase64EncodedValueContent().
             * @return the next stage of the container registry EncodedTaskStep update.
             */
            Update withOverridingValues(Map<String, OverridingValue> overridingValues);

            /**
             * The function that specifies a single value that will override the corresponding value specified under the function withBase64EncodedValueContent().
             *
             * @param name the name of the value to be overriden.
             * @param overridingValue the value of the value to be overriden.
             * @return the next stage of the container registry EncodedTaskStep update.
             */
            Update withOverridingValue(String name, OverridingValue overridingValue);
        }
    }

}
