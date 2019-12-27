docker_up:
	@docker-compose up -d

consul_up:
	@./register-services.sh && \
	 ./register-variables.sh

compile: 
	@cd example.app && mvn package

run:
	@cd example.app && java -jar target/appl.jar

up:     docker_up consul_up compile run

down:
	@docker-compose down

debug: docker_up consul_up 
