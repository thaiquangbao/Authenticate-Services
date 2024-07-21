FROM node:18
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . .
EXPOSE 9005
CMD [ "node", "index.js" ]
