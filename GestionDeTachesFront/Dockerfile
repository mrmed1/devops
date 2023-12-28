FROM node:16-alpine AS build
WORKDIR /app
COPY . .
RUN npm install --legacy-peer-deps && \
    npm run build
FROM nginx:alpine
EXPOSE 4200
COPY --from=build /app/dist/* /usr/share/nginx/html/
COPY /nginx.conf /etc/nginx/conf.d/default.conf
