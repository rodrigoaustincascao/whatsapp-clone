# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.137.0/containers/java/.devcontainer/base.Dockerfile
# See here image tags: https://hub.docker.com/_/microsoft-vscode-devcontainers

# Use VARIANT to choice the Java version: 11, 15
ARG VARIANT="21"
FROM mcr.microsoft.com/devcontainers/java:1-${VARIANT}

# [Optional] Install Maven or Gradle
ARG INSTALL_MAVEN="true"
ARG MAVEN_VERSION=""

ARG INSTALL_GRADLE="false"
ARG GRADLE_VERSION=""

RUN if [ "${INSTALL_MAVEN}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi \
    && if [ "${INSTALL_GRADLE}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi

# [Optional] Install V1_0_0__create_log_table.sql version of Node.js using nvm for front end dev
ARG INSTALL_NODE="true"
ARG NODE_VERSION="lts/*"
RUN if [ "${INSTALL_NODE}" = "true" ]; then su vscode -c "source /usr/local/share/nvm/nvm.sh && nvm install ${NODE_VERSION} 2>&1"; fi

# [Optional] Uncomment this section to install additional OS packages.
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
#     && apt-get -y install --no-install-recommends <your-package-list-here>

# [Optional] Uncomment this line to install global node packages.
RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g @angular/cli" 2>&1

WORKDIR /whatsapp-clone

