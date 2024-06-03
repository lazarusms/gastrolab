.PHONY: build run stop

IMAGE_NAME = gastrolab
CONTAINER_NAME = gastrolab-container

build:
	docker build -t $(IMAGE_NAME) .

run:
	docker run -d -p 8080:8080 --name $(CONTAINER_NAME) $(IMAGE_NAME)

stop:
	docker stop $(CONTAINER_NAME)
	docker rm $(CONTAINER_NAME)