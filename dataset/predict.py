import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import make_column_transformer
from sklearn.linear_model import LinearRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn import metrics

import numpy as np

df=pd.read_csv("data5000.csv")
print(df.head())


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


    # 정확도
    print(model.score(x_test,y_test))


    # 예제 데이터
    x_test = [
        [2000,2000,4000,'흰색','자동','가솔린',33,5,0,0,5000]
    ]

    x_test = transformer.transform(pd.DataFrame(x_test,columns=[
        'year', 'cc',  'distance', 'color', 'gear', 'fuel',  'option',  'seat',  'flooding',  'insurance',  'release'
    ]))
    

    y_predict = model.predict(x_test)
    print(y_predict[0]) 

# print("정확도 : ",metrics.accuracy_score(y_test,y_predict))