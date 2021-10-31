#!/bin/bash

declare project_dir=$(dirname $0)
declare project_version='0.0.1'
declare dc_app=${project_dir}/docker-compose.yaml

function start() {
    build_api
    echo "Starting docker containers...."
    docker-compose -f ${dc_app} up --build --force-recreate -d
    docker-compose -f ${dc_app} logs -f
}

function stop() {
    echo "Stopping docker containers...."
    docker-compose -f ${dc_app} stop
    docker-compose -f ${dc_app} rm -f
}

function restart() {
    stop
    sleep 5
    start
}
function build_api() {
    ./mvnw clean package -DskipTests
}

action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}
