package com.cofer.projects.retrosheet;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.cofer.projects.retrosheet.resources.RetrosheetResource;
import com.cofer.projects.retrosheet.health.TemplateHealthCheck;

public class RetrosheetApplication extends Application<RetrosheetConfiguration> {
    public static void main(String[] args) throws Exception {
        new RetrosheetApplication().run(args);
    }

    @Override
    public String getName() {
        return "retrosheet";
    }

    @Override
    public void initialize(Bootstrap<RetrosheetConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(RetrosheetConfiguration configuration,
                    Environment environment) {
        final RetrosheetResource resource = new RetrosheetResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}