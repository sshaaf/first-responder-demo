# buildah bud -f Dockerfile -t quay.io/jbride/emergency-console:0.0.1 .
# podman run -it --rm -d --publish 3000:3000 --name emergency-console quay.io/jbride/emergency-console:0.0.1

# ENV VARIABLES:
#  https://catalog.redhat.com/software/containers/ubi8/nodejs-12/5d3fff015a13461f5fb8635a?container-tabs=overview&gti-tabs=registry-tokens


FROM node:12

WORKDIR /usr/src/console

COPY . ./

COPY package*.json ./

RUN npm install
RUN npm run-script build

RUN chmod 777 dist/assets/js/env.js

EXPOSE 8080

ENTRYPOINT [ "npm", "run", "start" ]
