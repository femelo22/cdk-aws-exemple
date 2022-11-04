package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

import java.util.Arrays;

public class CursoAwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        VpcStack vpcStack = new VpcStack(app, "Minha-Vpc");

        ClusterStack cluster = new ClusterStack(app, "Cluster", vpcStack.getVpc());
        cluster.addDependency(vpcStack); // cria uma ordem logica para subir os recursos, precisamos da vpc primeiro, para dps criar o cluster

        Service01Stack service01Stack = new Service01Stack(app, "Service01", cluster.getCluster());
        service01Stack.addDependency(cluster); // Depende da criacao do Cluster para criar nosso service

        app.synth();
    }
}

