wget https://openebs.github.io/charts/openebs-operator.yaml
kubectl apply -f ./openebs-operator.yaml
kubectl get all -n openebs

