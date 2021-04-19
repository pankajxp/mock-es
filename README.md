# cmp-mock-gcp

A mock CMP API to test GCP POC. 

The API consists of simple customer data, currently hardcoded.  
The table below shows the fields which are prepended with cs/es depending on the layer. 

Field Name | Type
---------- | ----- 
customerID | Long  
name       | String
isSenior   | Boolean  
  

### Endpoints 
Enterprise Service: `8070:.../es/customer`  
Channel Service: `8071:.../cs/customer` 

## Deploy on GCP 

### Set up Using Local Terminal
1. Install the Google Cloud SDK using the following link: https://cloud.google.com/sdk/docs/quickstart  
Note: Python3 is needed for installation 
2. Set the default project and zone using 
```bash
gcloud init
```


To deploy this API on Kubernetes Engine on GCP: 
1. Ensure a project with billing is enabled on GCP Console
2. Clone or copy this code repository in to GCP Theia
3. Using cloud shell, install kubectl  
    ```bash 
    gcloud components install kubectl
    ```

### Deploy ES 
1. cd in to /cmp-mock-gcp/cmp-mock-customer-es
2. Ensure the API is working by running it and previewing on port 8070.
    ```bash 
    mvn spring-boot:run
    ```   

3. Create the .JAR file needed for the Dockerfile
    ```bash 
    mvn package
    ```

4. Build the docker image for the app:  
    ```bash 
    docker build -t gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-es:v1 .
    ```

5. Make sure the image built successfully
    ```bash 
    docker images
    ```

6. Run the docker image locally on Port 8070 to test it works   
    ```bash 
    docker run --rm -p 8070:8070 gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-es:v1
    ```

7. Enable the container registry API  
    ```bash
    gcloud services enable containerregistry.googleapis.com 
    gcloud auth configure-docker
    ```
8. Push the image on to container registry 
    ```bash
    docker push gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-es:v1
    ```


#### Using CloudShell and GCP Console
9. Create a Standard GKE cluster with the default settings (3 nodes), using the console  
10. Press Deploy and select the image pushed to container registry   
11. Deploy the image 
12. Go to the Workloads tab and Expose the deployment on port 8070, using TCP and a Load Balancer
13. Click on the external endpoint to test if it is working 

#### Using Local Terminal 
9. Create a cluster 
```bash
gcloud container clusters create <cluster-name> --num-nodes=3
```
10. Get credentials for the cluster
```bash
gcloud container clusters get-credentials <cluster-name>
```
11. Deploy the application
```bash   
kubectl create deployment <server-name> --image=gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-es:v1
```
12. Expose the deployment on port 8070 
```bash
kubectl expose deployment <server-name> --type LoadBalancer --port 8070
```
13. Insepct the pods
```bash
kubectl get pods
```
14. Inspect the service 
```bash
kubectl get service <server-name>
```
15. Copy the external IP address and test if it works in a browser

### Deploy CS 
1. cd in to /cmp-mock-gcp/cmp-mock-customer-cs
2. In application.properties, change `localhost:8070` in the `es.url` variable to the ES external endpoint from point 13 above
3. Ensure the CS API is working with the external endpoint by running it and previewing on port 8071.
    ```bash 
    mvn spring-boot:run
    ```   
4. Create the .JAR file needed for the Dockerfile
    ```bash 
    mvn package
    ```
5. Build the docker image for the app:  
    ```bash 
    docker build -t gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-cs:v1 .
    ```
6. Make sure the image built successfully
    ```bash 
    docker images
    ```
7. Run the docker image locally on Port 8071 to test it works   
    ```bash 
    docker run --rm -p 8071:8071 gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-cs:v1
    ```
8. Push the image on to container registry 
    ```bash
    docker push gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-cs:v1
    ```

#### Using CloudShell and GCP Console
9. Press Deploy and select the CS image pushed to container registry 
10. Deploy the image 
11. Go to the Workloads tab and Expose the deployment on port 8071, using TCP and a Load Balancer
12. Click on the external endpoint to test if it is working 


#### Using Local Terminal 

9. Deploy the application
```bash   
kubectl create deployment <server-name> --image=gcr.io/<Project ID>/cmp-mock-gcp/cmp-mock-customer-cs:v1
```
10. Expose the deployment on port 8071
```bash
kubectl expose deployment <server-name> --type LoadBalancer --port 8071
```
11. Insepct the pods
```bash
kubectl get pods
```
12. Inspect the service 
```bash
kubectl get service <server-name>
```
13. Copy the external IP address and test if it works in a browser
  
    
When you are done, delete everything to avoid being billed.  
Through local terminal, this can be done by: 
```bash 
kubectl delete service <server-name>
gcloud container clusters delete <cluster-name>
```
  
If you want to temporatrily stop the cluster, change the number of size of the node pool to 0:  
```bash 
gcloud container clusters resize <cluster-name> --node-pool default-pool  --num-nodes 0 --zone=us-central1-c
```
