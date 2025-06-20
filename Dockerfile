FROM advancedpricing.azurecr.io/openjdk11:0.0.4
COPY ipu-policy-service-version.jar /ipu-policy-service/
ENTRYPOINT java -jar /ipu-policy-service/ipu-policy-service-version.jar