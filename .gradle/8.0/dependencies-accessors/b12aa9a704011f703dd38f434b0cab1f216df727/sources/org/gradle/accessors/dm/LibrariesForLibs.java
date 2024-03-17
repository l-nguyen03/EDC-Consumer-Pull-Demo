package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final EdcLibraryAccessors laccForEdcLibraryAccessors = new EdcLibraryAccessors(owner);
    private final JakartaLibraryAccessors laccForJakartaLibraryAccessors = new JakartaLibraryAccessors(owner);
    private final JunitLibraryAccessors laccForJunitLibraryAccessors = new JunitLibraryAccessors(owner);
    private final KafkaLibraryAccessors laccForKafkaLibraryAccessors = new KafkaLibraryAccessors(owner);
    private final OkhttpLibraryAccessors laccForOkhttpLibraryAccessors = new OkhttpLibraryAccessors(owner);
    private final OpentelemetryLibraryAccessors laccForOpentelemetryLibraryAccessors = new OpentelemetryLibraryAccessors(owner);
    private final TestcontainersLibraryAccessors laccForTestcontainersLibraryAccessors = new TestcontainersLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for assertj (org.assertj:assertj-core)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAssertj() { return create("assertj"); }

        /**
         * Creates a dependency provider for awaitility (org.awaitility:awaitility)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAwaitility() { return create("awaitility"); }

        /**
         * Creates a dependency provider for jakartaJson (org.glassfish:jakarta.json)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJakartaJson() { return create("jakartaJson"); }

        /**
         * Creates a dependency provider for restAssured (io.rest-assured:rest-assured)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRestAssured() { return create("restAssured"); }

    /**
     * Returns the group of libraries at edc
     */
    public EdcLibraryAccessors getEdc() { return laccForEdcLibraryAccessors; }

    /**
     * Returns the group of libraries at jakarta
     */
    public JakartaLibraryAccessors getJakarta() { return laccForJakartaLibraryAccessors; }

    /**
     * Returns the group of libraries at junit
     */
    public JunitLibraryAccessors getJunit() { return laccForJunitLibraryAccessors; }

    /**
     * Returns the group of libraries at kafka
     */
    public KafkaLibraryAccessors getKafka() { return laccForKafkaLibraryAccessors; }

    /**
     * Returns the group of libraries at okhttp
     */
    public OkhttpLibraryAccessors getOkhttp() { return laccForOkhttpLibraryAccessors; }

    /**
     * Returns the group of libraries at opentelemetry
     */
    public OpentelemetryLibraryAccessors getOpentelemetry() { return laccForOpentelemetryLibraryAccessors; }

    /**
     * Returns the group of libraries at testcontainers
     */
    public TestcontainersLibraryAccessors getTestcontainers() { return laccForTestcontainersLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class EdcLibraryAccessors extends SubDependencyFactory {
        private final EdcApiLibraryAccessors laccForEdcApiLibraryAccessors = new EdcApiLibraryAccessors(owner);
        private final EdcAuthLibraryAccessors laccForEdcAuthLibraryAccessors = new EdcAuthLibraryAccessors(owner);
        private final EdcBuildLibraryAccessors laccForEdcBuildLibraryAccessors = new EdcBuildLibraryAccessors(owner);
        private final EdcConfigurationLibraryAccessors laccForEdcConfigurationLibraryAccessors = new EdcConfigurationLibraryAccessors(owner);
        private final EdcConnectorLibraryAccessors laccForEdcConnectorLibraryAccessors = new EdcConnectorLibraryAccessors(owner);
        private final EdcControlLibraryAccessors laccForEdcControlLibraryAccessors = new EdcControlLibraryAccessors(owner);
        private final EdcDataLibraryAccessors laccForEdcDataLibraryAccessors = new EdcDataLibraryAccessors(owner);
        private final EdcIamLibraryAccessors laccForEdcIamLibraryAccessors = new EdcIamLibraryAccessors(owner);
        private final EdcJerseyLibraryAccessors laccForEdcJerseyLibraryAccessors = new EdcJerseyLibraryAccessors(owner);
        private final EdcJettyLibraryAccessors laccForEdcJettyLibraryAccessors = new EdcJettyLibraryAccessors(owner);
        private final EdcJsonLibraryAccessors laccForEdcJsonLibraryAccessors = new EdcJsonLibraryAccessors(owner);
        private final EdcManagementLibraryAccessors laccForEdcManagementLibraryAccessors = new EdcManagementLibraryAccessors(owner);
        private final EdcMicrometerLibraryAccessors laccForEdcMicrometerLibraryAccessors = new EdcMicrometerLibraryAccessors(owner);
        private final EdcMonitorLibraryAccessors laccForEdcMonitorLibraryAccessors = new EdcMonitorLibraryAccessors(owner);
        private final EdcProvisionLibraryAccessors laccForEdcProvisionLibraryAccessors = new EdcProvisionLibraryAccessors(owner);
        private final EdcRuntimeLibraryAccessors laccForEdcRuntimeLibraryAccessors = new EdcRuntimeLibraryAccessors(owner);
        private final EdcTransferLibraryAccessors laccForEdcTransferLibraryAccessors = new EdcTransferLibraryAccessors(owner);
        private final EdcVaultLibraryAccessors laccForEdcVaultLibraryAccessors = new EdcVaultLibraryAccessors(owner);

        public EdcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for boot (org.eclipse.edc:boot)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBoot() { return create("edc.boot"); }

            /**
             * Creates a dependency provider for dsp (org.eclipse.edc:dsp)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDsp() { return create("edc.dsp"); }

            /**
             * Creates a dependency provider for http (org.eclipse.edc:http)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getHttp() { return create("edc.http"); }

            /**
             * Creates a dependency provider for junit (org.eclipse.edc:junit)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() { return create("edc.junit"); }

            /**
             * Creates a dependency provider for util (org.eclipse.edc:util)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUtil() { return create("edc.util"); }

        /**
         * Returns the group of libraries at edc.api
         */
        public EdcApiLibraryAccessors getApi() { return laccForEdcApiLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.auth
         */
        public EdcAuthLibraryAccessors getAuth() { return laccForEdcAuthLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.build
         */
        public EdcBuildLibraryAccessors getBuild() { return laccForEdcBuildLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.configuration
         */
        public EdcConfigurationLibraryAccessors getConfiguration() { return laccForEdcConfigurationLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.connector
         */
        public EdcConnectorLibraryAccessors getConnector() { return laccForEdcConnectorLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.control
         */
        public EdcControlLibraryAccessors getControl() { return laccForEdcControlLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.data
         */
        public EdcDataLibraryAccessors getData() { return laccForEdcDataLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.iam
         */
        public EdcIamLibraryAccessors getIam() { return laccForEdcIamLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.jersey
         */
        public EdcJerseyLibraryAccessors getJersey() { return laccForEdcJerseyLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.jetty
         */
        public EdcJettyLibraryAccessors getJetty() { return laccForEdcJettyLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.json
         */
        public EdcJsonLibraryAccessors getJson() { return laccForEdcJsonLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.management
         */
        public EdcManagementLibraryAccessors getManagement() { return laccForEdcManagementLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.micrometer
         */
        public EdcMicrometerLibraryAccessors getMicrometer() { return laccForEdcMicrometerLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.monitor
         */
        public EdcMonitorLibraryAccessors getMonitor() { return laccForEdcMonitorLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.provision
         */
        public EdcProvisionLibraryAccessors getProvision() { return laccForEdcProvisionLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.runtime
         */
        public EdcRuntimeLibraryAccessors getRuntime() { return laccForEdcRuntimeLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.transfer
         */
        public EdcTransferLibraryAccessors getTransfer() { return laccForEdcTransferLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.vault
         */
        public EdcVaultLibraryAccessors getVault() { return laccForEdcVaultLibraryAccessors; }

    }

    public static class EdcApiLibraryAccessors extends SubDependencyFactory {

        public EdcApiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.eclipse.edc:api-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("edc.api.core"); }

            /**
             * Creates a dependency provider for observability (org.eclipse.edc:api-observability)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getObservability() { return create("edc.api.observability"); }

    }

    public static class EdcAuthLibraryAccessors extends SubDependencyFactory {

        public EdcAuthLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for tokenbased (org.eclipse.edc:auth-tokenbased)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTokenbased() { return create("edc.auth.tokenbased"); }

    }

    public static class EdcBuildLibraryAccessors extends SubDependencyFactory {

        public EdcBuildLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for plugin (org.eclipse.edc.edc-build:org.eclipse.edc.edc-build.gradle.plugin)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPlugin() { return create("edc.build.plugin"); }

    }

    public static class EdcConfigurationLibraryAccessors extends SubDependencyFactory {

        public EdcConfigurationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for filesystem (org.eclipse.edc:configuration-filesystem)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getFilesystem() { return create("edc.configuration.filesystem"); }

    }

    public static class EdcConnectorLibraryAccessors extends SubDependencyFactory {

        public EdcConnectorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.eclipse.edc:connector-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("edc.connector.core"); }

    }

    public static class EdcControlLibraryAccessors extends SubDependencyFactory {
        private final EdcControlPlaneLibraryAccessors laccForEdcControlPlaneLibraryAccessors = new EdcControlPlaneLibraryAccessors(owner);

        public EdcControlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at edc.control.plane
         */
        public EdcControlPlaneLibraryAccessors getPlane() { return laccForEdcControlPlaneLibraryAccessors; }

    }

    public static class EdcControlPlaneLibraryAccessors extends SubDependencyFactory {
        private final EdcControlPlaneApiLibraryAccessors laccForEdcControlPlaneApiLibraryAccessors = new EdcControlPlaneApiLibraryAccessors(owner);

        public EdcControlPlaneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.eclipse.edc:control-plane-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("edc.control.plane.core"); }

            /**
             * Creates a dependency provider for spi (org.eclipse.edc:control-plane-spi)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSpi() { return create("edc.control.plane.spi"); }

        /**
         * Returns the group of libraries at edc.control.plane.api
         */
        public EdcControlPlaneApiLibraryAccessors getApi() { return laccForEdcControlPlaneApiLibraryAccessors; }

    }

    public static class EdcControlPlaneApiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public EdcControlPlaneApiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.eclipse.edc:control-plane-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("edc.control.plane.api"); }

            /**
             * Creates a dependency provider for client (org.eclipse.edc:control-plane-api-client)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getClient() { return create("edc.control.plane.api.client"); }

    }

    public static class EdcDataLibraryAccessors extends SubDependencyFactory {
        private final EdcDataPlaneLibraryAccessors laccForEdcDataPlaneLibraryAccessors = new EdcDataPlaneLibraryAccessors(owner);

        public EdcDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at edc.data.plane
         */
        public EdcDataPlaneLibraryAccessors getPlane() { return laccForEdcDataPlaneLibraryAccessors; }

    }

    public static class EdcDataPlaneLibraryAccessors extends SubDependencyFactory {
        private final EdcDataPlaneAwsLibraryAccessors laccForEdcDataPlaneAwsLibraryAccessors = new EdcDataPlaneAwsLibraryAccessors(owner);
        private final EdcDataPlaneAzureLibraryAccessors laccForEdcDataPlaneAzureLibraryAccessors = new EdcDataPlaneAzureLibraryAccessors(owner);
        private final EdcDataPlaneSelectorLibraryAccessors laccForEdcDataPlaneSelectorLibraryAccessors = new EdcDataPlaneSelectorLibraryAccessors(owner);

        public EdcDataPlaneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.eclipse.edc:data-plane-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() { return create("edc.data.plane.api"); }

            /**
             * Creates a dependency provider for client (org.eclipse.edc:data-plane-client)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getClient() { return create("edc.data.plane.client"); }

            /**
             * Creates a dependency provider for core (org.eclipse.edc:data-plane-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("edc.data.plane.core"); }

            /**
             * Creates a dependency provider for http (org.eclipse.edc:data-plane-http)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getHttp() { return create("edc.data.plane.http"); }

            /**
             * Creates a dependency provider for kafka (org.eclipse.edc:data-plane-kafka)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKafka() { return create("edc.data.plane.kafka"); }

            /**
             * Creates a dependency provider for spi (org.eclipse.edc:data-plane-spi)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSpi() { return create("edc.data.plane.spi"); }

            /**
             * Creates a dependency provider for util (org.eclipse.edc:data-plane-util)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUtil() { return create("edc.data.plane.util"); }

        /**
         * Returns the group of libraries at edc.data.plane.aws
         */
        public EdcDataPlaneAwsLibraryAccessors getAws() { return laccForEdcDataPlaneAwsLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.data.plane.azure
         */
        public EdcDataPlaneAzureLibraryAccessors getAzure() { return laccForEdcDataPlaneAzureLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.data.plane.selector
         */
        public EdcDataPlaneSelectorLibraryAccessors getSelector() { return laccForEdcDataPlaneSelectorLibraryAccessors; }

    }

    public static class EdcDataPlaneAwsLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneAwsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for s3 (org.eclipse.edc:data-plane-aws-s3)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getS3() { return create("edc.data.plane.aws.s3"); }

    }

    public static class EdcDataPlaneAzureLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneAzureLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for storage (org.eclipse.edc:data-plane-azure-storage)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getStorage() { return create("edc.data.plane.azure.storage"); }

    }

    public static class EdcDataPlaneSelectorLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneSelectorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.eclipse.edc:data-plane-selector-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() { return create("edc.data.plane.selector.api"); }

            /**
             * Creates a dependency provider for client (org.eclipse.edc:data-plane-selector-client)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getClient() { return create("edc.data.plane.selector.client"); }

            /**
             * Creates a dependency provider for core (org.eclipse.edc:data-plane-selector-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("edc.data.plane.selector.core"); }

    }

    public static class EdcIamLibraryAccessors extends SubDependencyFactory {

        public EdcIamLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for mock (org.eclipse.edc:iam-mock)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMock() { return create("edc.iam.mock"); }

    }

    public static class EdcJerseyLibraryAccessors extends SubDependencyFactory {

        public EdcJerseyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for micrometer (org.eclipse.edc:jersey-micrometer)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMicrometer() { return create("edc.jersey.micrometer"); }

    }

    public static class EdcJettyLibraryAccessors extends SubDependencyFactory {

        public EdcJettyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for micrometer (org.eclipse.edc:jetty-micrometer)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMicrometer() { return create("edc.jetty.micrometer"); }

    }

    public static class EdcJsonLibraryAccessors extends SubDependencyFactory {

        public EdcJsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ld (org.eclipse.edc:json-ld)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLd() { return create("edc.json.ld"); }

    }

    public static class EdcManagementLibraryAccessors extends SubDependencyFactory {

        public EdcManagementLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.eclipse.edc:management-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() { return create("edc.management.api"); }

    }

    public static class EdcMicrometerLibraryAccessors extends SubDependencyFactory {

        public EdcMicrometerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.eclipse.edc:micrometer-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("edc.micrometer.core"); }

    }

    public static class EdcMonitorLibraryAccessors extends SubDependencyFactory {
        private final EdcMonitorJdkLibraryAccessors laccForEdcMonitorJdkLibraryAccessors = new EdcMonitorJdkLibraryAccessors(owner);

        public EdcMonitorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at edc.monitor.jdk
         */
        public EdcMonitorJdkLibraryAccessors getJdk() { return laccForEdcMonitorJdkLibraryAccessors; }

    }

    public static class EdcMonitorJdkLibraryAccessors extends SubDependencyFactory {

        public EdcMonitorJdkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for logger (org.eclipse.edc:monitor-jdk-logger)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLogger() { return create("edc.monitor.jdk.logger"); }

    }

    public static class EdcProvisionLibraryAccessors extends SubDependencyFactory {
        private final EdcProvisionAwsLibraryAccessors laccForEdcProvisionAwsLibraryAccessors = new EdcProvisionAwsLibraryAccessors(owner);

        public EdcProvisionLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at edc.provision.aws
         */
        public EdcProvisionAwsLibraryAccessors getAws() { return laccForEdcProvisionAwsLibraryAccessors; }

    }

    public static class EdcProvisionAwsLibraryAccessors extends SubDependencyFactory {

        public EdcProvisionAwsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for s3 (org.eclipse.edc:provision-aws-s3)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getS3() { return create("edc.provision.aws.s3"); }

    }

    public static class EdcRuntimeLibraryAccessors extends SubDependencyFactory {

        public EdcRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for metamodel (org.eclipse.edc:runtime-metamodel)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMetamodel() { return create("edc.runtime.metamodel"); }

    }

    public static class EdcTransferLibraryAccessors extends SubDependencyFactory {
        private final EdcTransferDataLibraryAccessors laccForEdcTransferDataLibraryAccessors = new EdcTransferDataLibraryAccessors(owner);
        private final EdcTransferProcessLibraryAccessors laccForEdcTransferProcessLibraryAccessors = new EdcTransferProcessLibraryAccessors(owner);
        private final EdcTransferPullLibraryAccessors laccForEdcTransferPullLibraryAccessors = new EdcTransferPullLibraryAccessors(owner);

        public EdcTransferLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at edc.transfer.data
         */
        public EdcTransferDataLibraryAccessors getData() { return laccForEdcTransferDataLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.transfer.process
         */
        public EdcTransferProcessLibraryAccessors getProcess() { return laccForEdcTransferProcessLibraryAccessors; }

        /**
         * Returns the group of libraries at edc.transfer.pull
         */
        public EdcTransferPullLibraryAccessors getPull() { return laccForEdcTransferPullLibraryAccessors; }

    }

    public static class EdcTransferDataLibraryAccessors extends SubDependencyFactory {

        public EdcTransferDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for plane (org.eclipse.edc:transfer-data-plane)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPlane() { return create("edc.transfer.data.plane"); }

    }

    public static class EdcTransferProcessLibraryAccessors extends SubDependencyFactory {

        public EdcTransferProcessLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.eclipse.edc:transfer-process-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() { return create("edc.transfer.process.api"); }

    }

    public static class EdcTransferPullLibraryAccessors extends SubDependencyFactory {
        private final EdcTransferPullHttpLibraryAccessors laccForEdcTransferPullHttpLibraryAccessors = new EdcTransferPullHttpLibraryAccessors(owner);

        public EdcTransferPullLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at edc.transfer.pull.http
         */
        public EdcTransferPullHttpLibraryAccessors getHttp() { return laccForEdcTransferPullHttpLibraryAccessors; }

    }

    public static class EdcTransferPullHttpLibraryAccessors extends SubDependencyFactory {
        private final EdcTransferPullHttpDynamicLibraryAccessors laccForEdcTransferPullHttpDynamicLibraryAccessors = new EdcTransferPullHttpDynamicLibraryAccessors(owner);

        public EdcTransferPullHttpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for receiver (org.eclipse.edc:transfer-pull-http-receiver)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getReceiver() { return create("edc.transfer.pull.http.receiver"); }

        /**
         * Returns the group of libraries at edc.transfer.pull.http.dynamic
         */
        public EdcTransferPullHttpDynamicLibraryAccessors getDynamic() { return laccForEdcTransferPullHttpDynamicLibraryAccessors; }

    }

    public static class EdcTransferPullHttpDynamicLibraryAccessors extends SubDependencyFactory {

        public EdcTransferPullHttpDynamicLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for receiver (org.eclipse.edc:transfer-pull-http-dynamic-receiver)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getReceiver() { return create("edc.transfer.pull.http.dynamic.receiver"); }

    }

    public static class EdcVaultLibraryAccessors extends SubDependencyFactory {

        public EdcVaultLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for azure (org.eclipse.edc:vault-azure)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAzure() { return create("edc.vault.azure"); }

            /**
             * Creates a dependency provider for filesystem (org.eclipse.edc:vault-filesystem)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getFilesystem() { return create("edc.vault.filesystem"); }

    }

    public static class JakartaLibraryAccessors extends SubDependencyFactory {

        public JakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for rsApi (jakarta.ws.rs:jakarta.ws.rs-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRsApi() { return create("jakarta.rsApi"); }

    }

    public static class JunitLibraryAccessors extends SubDependencyFactory {
        private final JunitJupiterLibraryAccessors laccForJunitJupiterLibraryAccessors = new JunitJupiterLibraryAccessors(owner);

        public JunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for pioneer (org.junit-pioneer:junit-pioneer)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPioneer() { return create("junit.pioneer"); }

        /**
         * Returns the group of libraries at junit.jupiter
         */
        public JunitJupiterLibraryAccessors getJupiter() { return laccForJunitJupiterLibraryAccessors; }

    }

    public static class JunitJupiterLibraryAccessors extends SubDependencyFactory {

        public JunitJupiterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.junit.jupiter:junit-jupiter-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() { return create("junit.jupiter.api"); }

            /**
             * Creates a dependency provider for engine (org.junit.jupiter:junit-jupiter-engine)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getEngine() { return create("junit.jupiter.engine"); }

            /**
             * Creates a dependency provider for params (org.junit.jupiter:junit-jupiter-params)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getParams() { return create("junit.jupiter.params"); }

    }

    public static class KafkaLibraryAccessors extends SubDependencyFactory {

        public KafkaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for clients (org.apache.kafka:kafka-clients)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getClients() { return create("kafka.clients"); }

    }

    public static class OkhttpLibraryAccessors extends SubDependencyFactory {

        public OkhttpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for mockwebserver (com.squareup.okhttp3:mockwebserver)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMockwebserver() { return create("okhttp.mockwebserver"); }

    }

    public static class OpentelemetryLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public OpentelemetryLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for opentelemetry (io.opentelemetry.javaagent:opentelemetry-javaagent)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("opentelemetry"); }

            /**
             * Creates a dependency provider for annotations (io.opentelemetry:opentelemetry-extension-annotations)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAnnotations() { return create("opentelemetry.annotations"); }

    }

    public static class TestcontainersLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final TestcontainersJunitLibraryAccessors laccForTestcontainersJunitLibraryAccessors = new TestcontainersJunitLibraryAccessors(owner);

        public TestcontainersLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for testcontainers (org.testcontainers:testcontainers)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("testcontainers"); }

            /**
             * Creates a dependency provider for kafka (org.testcontainers:kafka)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKafka() { return create("testcontainers.kafka"); }

        /**
         * Returns the group of libraries at testcontainers.junit
         */
        public TestcontainersJunitLibraryAccessors getJunit() { return laccForTestcontainersJunitLibraryAccessors; }

    }

    public static class TestcontainersJunitLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public TestcontainersJunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (org.testcontainers:junit-jupiter)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("testcontainers.junit"); }

            /**
             * Creates a dependency provider for jupiter (org.testcontainers:junit-jupiter)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJupiter() { return create("testcontainers.junit.jupiter"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final JakartaVersionAccessors vaccForJakartaVersionAccessors = new JakartaVersionAccessors(providers, config);
        private final JunitVersionAccessors vaccForJunitVersionAccessors = new JunitVersionAccessors(providers, config);
        private final OkhttpVersionAccessors vaccForOkhttpVersionAccessors = new OkhttpVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: assertj (3.25.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAssertj() { return getVersion("assertj"); }

            /**
             * Returns the version associated to this alias: awaitility (4.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAwaitility() { return getVersion("awaitility"); }

            /**
             * Returns the version associated to this alias: edc (0.4.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getEdc() { return getVersion("edc"); }

            /**
             * Returns the version associated to this alias: jupiter (5.10.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJupiter() { return getVersion("jupiter"); }

            /**
             * Returns the version associated to this alias: kafkaClients (3.7.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKafkaClients() { return getVersion("kafkaClients"); }

            /**
             * Returns the version associated to this alias: openTelemetry (1.18.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getOpenTelemetry() { return getVersion("openTelemetry"); }

            /**
             * Returns the version associated to this alias: restAssured (5.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRestAssured() { return getVersion("restAssured"); }

            /**
             * Returns the version associated to this alias: rsApi (3.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRsApi() { return getVersion("rsApi"); }

            /**
             * Returns the version associated to this alias: testcontainers (1.19.6)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTestcontainers() { return getVersion("testcontainers"); }

        /**
         * Returns the group of versions at versions.jakarta
         */
        public JakartaVersionAccessors getJakarta() { return vaccForJakartaVersionAccessors; }

        /**
         * Returns the group of versions at versions.junit
         */
        public JunitVersionAccessors getJunit() { return vaccForJunitVersionAccessors; }

        /**
         * Returns the group of versions at versions.okhttp
         */
        public OkhttpVersionAccessors getOkhttp() { return vaccForOkhttpVersionAccessors; }

    }

    public static class JakartaVersionAccessors extends VersionFactory  {

        public JakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: jakarta.json (2.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJson() { return getVersion("jakarta.json"); }

    }

    public static class JunitVersionAccessors extends VersionFactory  {

        public JunitVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: junit.pioneer (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPioneer() { return getVersion("junit.pioneer"); }

    }

    public static class OkhttpVersionAccessors extends VersionFactory  {

        public OkhttpVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: okhttp.mockwebserver (5.0.0-alpha.12)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMockwebserver() { return getVersion("okhttp.mockwebserver"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for shadow to the plugin id 'com.github.johnrengelman.shadow'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getShadow() { return createPlugin("shadow"); }

    }

}
