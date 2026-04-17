#------------------------------------------------------------------------------
# Prometheus
git clone https://github.com/prometheus-operator/kube-prometheus.git
cd kube-prometheus
kubectl create -f manifests/setup/
#customresourcedefinition.apiextensions.k8s.io/alertmanagerconfigs.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/alertmanagers.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/podmonitors.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/probes.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/prometheuses.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/prometheusagents.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/prometheusrules.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/scrapeconfigs.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/servicemonitors.monitoring.coreos.com created
#customresourcedefinition.apiextensions.k8s.io/thanosrulers.monitoring.coreos.com created
#namespace/monitoring created

#分别修改 manifests/prometheus-service.yaml、manifests/alertmanager-service.yaml、manifests/grafana-service.yaml 中的 spec.type 为 NodePort 方便测试访问
#   type: NodePort
kubectl apply -f manifests/
#alertmanager.monitoring.coreos.com/main created
#networkpolicy.networking.k8s.io/alertmanager-main created
#poddisruptionbudget.policy/alertmanager-main created
#prometheusrule.monitoring.coreos.com/alertmanager-main-rules created
#secret/alertmanager-main created
#service/alertmanager-main created
#serviceaccount/alertmanager-main created
#servicemonitor.monitoring.coreos.com/alertmanager-main created
#clusterrole.rbac.authorization.k8s.io/blackbox-exporter created
#clusterrolebinding.rbac.authorization.k8s.io/blackbox-exporter created
#configmap/blackbox-exporter-configuration created
#deployment.apps/blackbox-exporter created
#networkpolicy.networking.k8s.io/blackbox-exporter created
#service/blackbox-exporter created
#serviceaccount/blackbox-exporter created
#servicemonitor.monitoring.coreos.com/blackbox-exporter created
#secret/grafana-config created
#secret/grafana-datasources created
#configmap/grafana-dashboard-alertmanager-overview created
#configmap/grafana-dashboard-apiserver created
#configmap/grafana-dashboard-cluster-total created
#configmap/grafana-dashboard-controller-manager created
#configmap/grafana-dashboard-grafana-overview created
#configmap/grafana-dashboard-k8s-resources-cluster created
#configmap/grafana-dashboard-k8s-resources-multicluster created
#configmap/grafana-dashboard-k8s-resources-namespace created
#configmap/grafana-dashboard-k8s-resources-node created
#configmap/grafana-dashboard-k8s-resources-pod created
#configmap/grafana-dashboard-k8s-resources-windows-cluster created
#configmap/grafana-dashboard-k8s-resources-windows-namespace created
#configmap/grafana-dashboard-k8s-resources-windows-pod created
#configmap/grafana-dashboard-k8s-resources-workload created
#configmap/grafana-dashboard-k8s-resources-workloads-namespace created
#configmap/grafana-dashboard-k8s-windows-cluster-rsrc-use created
#configmap/grafana-dashboard-k8s-windows-node-rsrc-use created
#configmap/grafana-dashboard-kubelet created
#configmap/grafana-dashboard-namespace-by-pod created
#configmap/grafana-dashboard-namespace-by-workload created
#configmap/grafana-dashboard-node-cluster-rsrc-use created
#configmap/grafana-dashboard-node-rsrc-use created
#configmap/grafana-dashboard-nodes-aix created
#configmap/grafana-dashboard-nodes-darwin created
#configmap/grafana-dashboard-nodes created
#configmap/grafana-dashboard-persistentvolumesusage created
#configmap/grafana-dashboard-pod-total created
#configmap/grafana-dashboard-prometheus-remote-write created
#configmap/grafana-dashboard-prometheus created
#configmap/grafana-dashboard-proxy created
#configmap/grafana-dashboard-scheduler created
#configmap/grafana-dashboard-workload-total created
#configmap/grafana-dashboards created
#deployment.apps/grafana created
#networkpolicy.networking.k8s.io/grafana created
#prometheusrule.monitoring.coreos.com/grafana-rules created
#service/grafana created
#serviceaccount/grafana created
#servicemonitor.monitoring.coreos.com/grafana created
#prometheusrule.monitoring.coreos.com/kube-prometheus-rules created
#clusterrole.rbac.authorization.k8s.io/kube-state-metrics created
#clusterrolebinding.rbac.authorization.k8s.io/kube-state-metrics created
#deployment.apps/kube-state-metrics created
#networkpolicy.networking.k8s.io/kube-state-metrics created
#prometheusrule.monitoring.coreos.com/kube-state-metrics-rules created
#service/kube-state-metrics created
#serviceaccount/kube-state-metrics created
#servicemonitor.monitoring.coreos.com/kube-state-metrics created
#prometheusrule.monitoring.coreos.com/kubernetes-monitoring-rules created
#servicemonitor.monitoring.coreos.com/kube-apiserver created
#servicemonitor.monitoring.coreos.com/coredns created
#servicemonitor.monitoring.coreos.com/kube-controller-manager created
#servicemonitor.monitoring.coreos.com/kube-scheduler created
#servicemonitor.monitoring.coreos.com/kubelet created
#clusterrole.rbac.authorization.k8s.io/node-exporter created
#clusterrolebinding.rbac.authorization.k8s.io/node-exporter created
#daemonset.apps/node-exporter created
#networkpolicy.networking.k8s.io/node-exporter created
#prometheusrule.monitoring.coreos.com/node-exporter-rules created
#service/node-exporter created
#serviceaccount/node-exporter created
#servicemonitor.monitoring.coreos.com/node-exporter created
#clusterrole.rbac.authorization.k8s.io/prometheus-k8s created
#clusterrolebinding.rbac.authorization.k8s.io/prometheus-k8s created
#networkpolicy.networking.k8s.io/prometheus-k8s created
#poddisruptionbudget.policy/prometheus-k8s created
#prometheus.monitoring.coreos.com/k8s created
#prometheusrule.monitoring.coreos.com/prometheus-k8s-prometheus-rules created
#rolebinding.rbac.authorization.k8s.io/prometheus-k8s-config created
#rolebinding.rbac.authorization.k8s.io/prometheus-k8s created
#rolebinding.rbac.authorization.k8s.io/prometheus-k8s created
#rolebinding.rbac.authorization.k8s.io/prometheus-k8s created
#role.rbac.authorization.k8s.io/prometheus-k8s-config created
#role.rbac.authorization.k8s.io/prometheus-k8s created
#role.rbac.authorization.k8s.io/prometheus-k8s created
#role.rbac.authorization.k8s.io/prometheus-k8s created
#service/prometheus-k8s created
#serviceaccount/prometheus-k8s created
#servicemonitor.monitoring.coreos.com/prometheus-k8s created
#apiservice.apiregistration.k8s.io/v1beta1.metrics.k8s.io configured
#clusterrole.rbac.authorization.k8s.io/prometheus-adapter created
#clusterrole.rbac.authorization.k8s.io/system:aggregated-metrics-reader configured
#clusterrolebinding.rbac.authorization.k8s.io/prometheus-adapter created
#clusterrolebinding.rbac.authorization.k8s.io/resource-metrics:system:auth-delegator created
#clusterrole.rbac.authorization.k8s.io/resource-metrics-server-resources created
#configmap/adapter-config created
#deployment.apps/prometheus-adapter created
#networkpolicy.networking.k8s.io/prometheus-adapter created
#poddisruptionbudget.policy/prometheus-adapter created
#rolebinding.rbac.authorization.k8s.io/resource-metrics-auth-reader created
#service/prometheus-adapter created
#serviceaccount/prometheus-adapter created
#servicemonitor.monitoring.coreos.com/prometheus-adapter created
#clusterrole.rbac.authorization.k8s.io/prometheus-operator created
#clusterrolebinding.rbac.authorization.k8s.io/prometheus-operator created
#deployment.apps/prometheus-operator created
#networkpolicy.networking.k8s.io/prometheus-operator created
#prometheusrule.monitoring.coreos.com/prometheus-operator-rules created
#service/prometheus-operator created
#serviceaccount/prometheus-operator created
#servicemonitor.monitoring.coreos.com/prometheus-operator created

kubectl get all -n monitoring

#----------------------------------------------------------------------------
# 修改/etc/hosts
kubectl get svc -n monitoring
kubectl get ingress -n monitoring
#NAME                 CLASS   HOSTS                                                                 ADDRESS        PORTS   AGE
#prometheus-ingress   nginx   grafana.wolfcode.cn,prometheus.wolfcode.cn,alertmanager.wolfcode.cn   172.16.11.19   80      17s

172.16.11.19 grafana.wolfcode.cn
172.16.11.19 prometheus.wolfcode.cn
172.16.11.19 alertmanager.wolfcode.cn
