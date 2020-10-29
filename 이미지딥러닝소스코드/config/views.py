from django.shortcuts import render
from django.http import JsonResponse
import base64
from django.core.files.base import ContentFile
from django.core.files.storage import default_storage
from django.conf import settings 
from tensorflow.python.keras.backend import set_session
from keras.preprocessing.image import load_img
from keras.preprocessing.image import img_to_array
from keras.applications.imagenet_utils import decode_predictions
import matplotlib.pyplot as plt
import numpy as np
from keras.applications import vgg16
import datetime
import traceback
import os
from tensorflow.keras.models import load_model


def index(request):
    if  request.method == "POST":
        f = request.FILES['sentFile'] # here you get the files needed
        response = {}
        file_name = "pic.jpg"
        file_name_2 = default_storage.save(file_name, f)
        file_url = default_storage.url(file_name_2)

        # 케라스/텐서플로
        # 이미지 전처리
        original = load_img(file_url, target_size=(150, 150))
        
        img = original.convert("RGB")
        img = img.resize((150,150))
        data = np.asarray(img)
        data= data[np.newaxis, :]
        print(type(data), data.shape)
        predictions = settings.VGG_MODEL.predict(data)
        print(predictions.shape)
        data = predictions.flatten()
        data = data[np.newaxis, :]
        print( data.shape)
        model_path = os.path.join(settings.BASE_DIR, 'config/model/my_model.h5')
        model = load_model(model_path)
        
        prediction= model.predict(data)
        x=data
        np.set_printoptions(formatter={'float': lambda x: "{0:0.3f}".format(x)})
        cnt = 0

        print(prediction)
        result ="실패" if model.predict_classes(data)==0 else"성공"
        pred_proba  = model.predict_proba(data)

        # response = str(result)
        print(result)
        return render(request,'homepage.html',{'result':(result,pred_proba)})

    else:
        return render(request,'homepage.html')
        
    #     numpy_image = img_to_array(original)
        
    #     image_batch = np.expand_dims(numpy_image, axis=0)

    #     # prepare the image for the VGG model
    #     processed_image = vgg16.preprocess_input(image_batch.copy())
    #     p
    #     # get the predicted probabilities for each class
    #     # with settings.GRAPH1.as_default():
    #     #     set_session(settings.SESS)
        # predictions = settings.VGG_MODEL.predict(processed_image)

    #     label = decode_predictions(predictions)
    #     label = list(label)[0]
    #     response['name'] = str(label)
    #     return render(request,'homepage.html',response)
    # else:
    #     return render(request,'homepage.html')