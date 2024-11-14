# 

## Model
www.msaez.io/#/storming/applyissue-attr

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- application
- issue
- alarm
- integration
- edms


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- application
```
 http :8088/complaints 신청번호="신청 번호" 서식일렬번호="서식 일렬번호" 서비스id="서비스ID" 서비스분류코드="서비스 분류 코드" 신청인명="신청인 명" 주민등록번호="주민등록번호" 신청처리결과코드="신청처리결과코드" 신청일시="신청 일시" 등록일시="등록 일시" 수정일시="수정 일시" 구비서류="구비서류" 신청수령방법="신청수령방법" 신청서파일="신청서파일" 
```
- issue
```
 http :8088/발급 발급파일일련번호="발급 파일 일련번호" 신청번호="신청 번호" 파일Id="파일 ID" 파일명="파일 명" 파일경로명="파일 경로 명" 파일확장자명="파일 확장자 명" 파일크기="파일 크기" 파일유형코드="파일 유형 코드" 파일순서="파일 순서" 파일유형코드="파일유형코드" 
```
- alarm
```
 http :8088/알림 id="id" 민원id="민원id" 민원접수번호="민원접수번호" 신청알림발송여부="신청알림발송여부" 처리알림발송여부="처리알림발송여부" 
```
- integration
```
 http :8088/integrations 연계Id="연계 id" 신청번호="신청 번호" 서비스Id="서비스 ID" 유형일렬번호="유형일렬번호" 연계인터페이스="연계 인터페이스" 송신데이터="송신 데이터" 수신데이터="수신 데이터" 결과코드="결과 코드" 결과메시지="결과 메시지" 송신일시="송신 일시" 수신일시="수신 일시" 등록일시="등록 일시" 수정일시="수정 일시" 
```
- edms
```
 http :8088/edms id="id" 신청번호="신청 번호" 파일id="파일id" 파일명="파일 명" 저장유무="저장유무" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

