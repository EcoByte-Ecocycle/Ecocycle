AI_README.me

### Data & AI 모듈, 설계
- 재활용 분류 모델 input : image url - Output : 재활용 분석 결과.json
- Directory 구성
Main directory Src directory, inference.py, train.py, Read.me Inference로 모델작업, train으로 학습
Src directory Dataset, __Init__.py, dataset.py, loss.py, model.py, trainer.py
<img width="205" alt="image" src="https://user-images.githubusercontent.com/88193038/231921406-f96fdc47-300f-412b-bbfb-9ae5d411671b.png">

<Dataset 폴더 내부>


• 데이터 수집, 모델 학습 설계
0.	인공지능 모델 서빙 과정

<img width="471" alt="image" src="https://user-images.githubusercontent.com/88193038/231921975-d3e4629e-743f-42e6-a68f-56747101546c.png">

#### 1. 데이터 수집
- 크롤링

```
from selenium import webdriver
from selenium.webdriver.common.by import By
```

필요 패키지 설치 – selenium webdriver
```
imgs = driver.find_elements_by_css_selector(".rg_i.Q4LuWd") //이미지 선택
Imageurl, 저장 path, 갯수 300, img src tag
```

<img width="447" alt="image" src="https://user-images.githubusercontent.com/88193038/231922949-4e93227a-6434-48d5-9d91-1b4ae3b058ff.png">

 
#### 2. 공개 데이터셋 (비영리, 출처 표기시 사용 가능)
Kaggle, 공공데이터 포털, Naver Connect, AI Hub 데이터셋

  
#### 3. 직접 촬영



### 데이터 수집 계획 – 카테고리 분류
 
![image](https://user-images.githubusercontent.com/88193038/231923056-ef0d30f8-8576-4d36-8c01-fdc5c412dd0e.png)

<img width="481" alt="image" src="https://user-images.githubusercontent.com/88193038/231923119-2c55d49f-36df-4145-b124-c0d4d5387a3c.png">


### 전처리

<img width="469" alt="image" src="https://user-images.githubusercontent.com/88193038/231923239-37381281-fcdb-4036-8c8d-ef498c498597.png">

Path 불러오기 - 크기 조정 – 이미지 변형
  

 
![image](https://user-images.githubusercontent.com/88193038/231923282-7f5d142a-7163-408a-bb52-2d6c8fb1cee0.png)





