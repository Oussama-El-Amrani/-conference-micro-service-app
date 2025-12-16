#!/bin/bash
set -e

echo "Starting Multi-Service Jib Build"

SERVICE_MODULES=(
	"conference-service"
	"config-service"
	"discovery-service"
	"gateway-service"
	"keynote-service"
)

echo "Building and creating Docker images for all services using Maven Jib..."

MODULE_LIST=$(IFS=,; echo "${SERVICE_MODULES[*]}")

mvn clean package jib:dockerBuild \
    --projects "$MODULE_LIST" \
    --also-make \
    -DskipTests=true

echo "-------------------------------"  
echo "All service images build succesfully!"
echo "You can view your images with: docker images"
