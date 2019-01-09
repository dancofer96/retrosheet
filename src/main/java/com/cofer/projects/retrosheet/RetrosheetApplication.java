package com.cofer.projects.retrosheet;

import com.cofer.projects.retrosheet.core.Game;
import com.cofer.projects.retrosheet.db.GameDAO;
import com.cofer.projects.retrosheet.resources.GameResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.cofer.projects.retrosheet.resources.RetrosheetResource;
import com.cofer.projects.retrosheet.health.TemplateHealthCheck;
import io.dropwizard.hibernate.HibernateBundle;


public class RetrosheetApplication extends Application<RetrosheetConfiguration> {
    public static void main(String[] args) throws Exception {
        new RetrosheetApplication().run(args);
    }

    private final HibernateBundle<RetrosheetConfiguration> hibernate =
            new HibernateBundle<RetrosheetConfiguration>(Game.class) {
                //@Override
                public DataSourceFactory getDataSourceFactory(RetrosheetConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "retrosheet";
    }

    @Override
    public void initialize(Bootstrap<RetrosheetConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
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

        final GameDAO gameDAO = new GameDAO(hibernate.getSessionFactory());
        environment.jersey().register(new GameResource(gameDAO));
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}