#FROM node:16-alpine AS build
#WORKDIR /app
#COPY . .
#RUN npm install --legacy-peer-deps && \
#    npm run build --base-href=app
#FROM nginx:alpine
#EXPOSE 4200
#COPY --from=build /app/dist/* /usr/share/nginx/html/
#COPY /nginx.conf /etc/nginx/conf.d/default.conf


FROM node:16-alpine AS build

WORKDIR /home/mohamed
COPY . .
RUN npm install --legacy-peer-deps && \
    npm run build --base-href=app

FROM nginx:alpine
EXPOSE 4200

# Set the working directory to /home/mohamed in the final stage
WORKDIR /home/mohamed

# Copy the built artifacts from the build stage to /home/mohamed in the final stage
COPY --from=build /home/mohamed/dist/* /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf

