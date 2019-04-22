cd accountmanager
source ./env-variable.sh
mvn clean package
docker build -t user-app .
cd ..
cd musemanager
source ./env-variable.sh
mvn clean package
docker build -t muse-app .
cd ..
