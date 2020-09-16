from django.shortcuts import render

from rest_framework import viewsets
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from rest_framework.views import APIView
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import make_column_transformer
from sklearn.linear_model import LinearRegression


@csrf_exempt
def post_car(request) :
    data=JSONParser().parse(request)

    year=int(data['year'])
    cc=int(data['cc'])
    distance=int(data['distance'])
    color=data['color']
    gear=data['gear']
    fuel=data['fuel']
    option=int(data['option'])
    seat=int(data['seat'])
    flooding=int(data['flooding'])
    insurance=int(data['insurance'])
    release=int(data['release'])

    result=predict_car(year,cc,distance,color,gear,fuel,option,seat,flooding,insurance,release)

    return JsonResponse({'result': result}, status=200)



def predict_car(year,cc,distance,color,gear,fuel,option,seat,flooding,insurance,release) :

    df=pd.read_csv("./predict/data.csv")
    print(df.head())


    #테스트 하기 위한 임시변수
    #나중에 이거 평균 계산(정확도 낮은거 내치기)
    sum=0
    
    for i in range(10) :
        x = df[['year', 'cc',  'distance', 'color', 'gear', 'fuel',  'option',  'seat',  'flooding',  'insurance',  'release']]
        y = df[['price']]

        x_train, x_test, y_train, y_test = train_test_split(x, y, train_size=0.9, test_size=0.1)


        transformer = make_column_transformer(
            (OneHotEncoder(), ['color', 'gear', 'fuel']),
            remainder='passthrough')

        transformer.fit(x_train)
        x_train=transformer.transform(x_train)
        x_test=transformer.transform(x_test)

        y_train=y_train.to_numpy()
        y_test=y_test.to_numpy()

        ################################################################################
        # 선형회귀
        model=LinearRegression()
        model.fit(x_train,y_train)

        # 의사결정트리
        # model=DecisionTreeClassifier()
        # model.fit(x_train,y_train)

        # 랜덤 포레스트
        # model=RandomForestClassifier(n_estimators=10)
        # model.fit(x_train, np.ravel(y_train,order='C'))
        ################################################################################

        # 정확도    낮은거 내치기
        print(model.score(x_test,y_test))

        # 파리미터 입력값들
        x_test = [
            # ['2000','2000','4000','흰색','자동','가솔린','33','5','0','0','5000']
            [year,cc,distance,color,gear,fuel,option,seat,flooding,insurance,release]
        ]

        x_test = transformer.transform(pd.DataFrame(x_test,columns=[
            'year', 'cc',  'distance', 'color', 'gear', 'fuel',  'option',  'seat',  'flooding',  'insurance',  'release'
        ]))
        
        # print("정확도 : ",metrics.accuracy_score(y_test,y_predict))

        y_predict = model.predict(x_test)
        sum += y_predict
        print(">>",y_predict[0]) 

    return sum/10
