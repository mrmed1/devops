#FROM node:16-alpine AS build
#WORKDIR /app
#COPY . .
#RUN npm install --legacy-peer-deps && \
#    npm run build --base-href=app
#FROM nginx:alpine
#EXPOSE 4200
#COPY --from=build /app/dist/* /usr/share/nginx/html/
#COPY /nginx.conf /etc/nginx/conf.d/default.conf

# Stage 1: Build Angular App
FROM node:16-alpine AS build

# Set the working directory for the build stage
WORKDIR /home/mohamed

# Copy the entire project into the container
COPY . .

# Install dependencies and build the Angular app
RUN npm install --legacy-peer-deps && \
    npm run build --base-href=app

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Expose port 80 for Nginx
EXPOSE 80

# Set the working directory in the final stage
WORKDIR /usr/share/nginx/html/app

# Copy the built artifacts from the build stage to the Nginx server
COPY --from=build /home/mohamed/dist/* .

# Copy the Nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.conf

