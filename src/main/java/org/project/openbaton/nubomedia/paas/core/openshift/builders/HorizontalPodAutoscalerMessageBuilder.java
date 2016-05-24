package org.project.openbaton.nubomedia.paas.core.openshift.builders;

import org.project.openbaton.nubomedia.paas.model.openshift.HPASpec;
import org.project.openbaton.nubomedia.paas.model.openshift.HPASpecCpuMetrics;
import org.project.openbaton.nubomedia.paas.model.openshift.HorizontalPodAutoscaler;
import org.project.openbaton.nubomedia.paas.model.openshift.Metadata;

/**
 * Created by maa on 07.02.16.
 */
public class HorizontalPodAutoscalerMessageBuilder {

    private String appName;
    private int minReplicas;
    private int maxReplicas;
    private int cpuUtilization;

    public HorizontalPodAutoscalerMessageBuilder(String appName, int minReplicas, int maxReplicas, int cpuUtilization) {
        this.appName = appName;
        this.minReplicas = minReplicas;
        this.maxReplicas = maxReplicas;
        this.cpuUtilization = cpuUtilization;
    }

    public HorizontalPodAutoscaler buildMessage(){

        Metadata metadata = new Metadata(appName + "-hpa",null,null);
        HPASpecCpuMetrics metrics = new HPASpecCpuMetrics(cpuUtilization);
        HPASpec.HPASpecScaleRef scaleRef = new HPASpec.HPASpecScaleRef(appName + "-dc");
        HPASpec spec = new HPASpec(scaleRef,minReplicas,maxReplicas,metrics);

        return new HorizontalPodAutoscaler(metadata,spec);
    }
}