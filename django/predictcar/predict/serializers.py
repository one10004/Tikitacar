from rest_framework import serializers 
from .models import Car

class CarSerializer(serializers.ModelSerializer): 
    class Meta: 
        model = Car # 모델 설정 
        fields = ( 'year', 'cc',  'distance', 'color', 'gear', 'fuel',  'option',  'seat',  'flooding',  'insurance',  'release') # 필드 설정
