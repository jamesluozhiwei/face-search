# coding: utf-8
import face_recognition
import numpy as np
from flask import Flask, jsonify, request, redirect, abort, Response, app
from flask_cors import CORS

from PIL import Image
import base64
import json
from io import BytesIO
import re
import tensorflow as tf
import time
import hmac
import logging

import face_encoding_data_service
import train_model_service


@app.route('/person/register', methods=['GET', 'POST'])
def person_register():
    if request.method == 'POST':
        base64_img = json.loads(request.get_data())['imgData']
        person_id = json.loads(request.get_data())['person_id']
        open_key = json.loads(request.get_data())['open_key']
        for i in range(len(base64_img)):
            base64_data = re.sub('^data:image/.+;base64,', '', base64_img[i])
            img = base64.b64decode(base64_data)
            img_data = BytesIO(img)
            im = Image.open(img_data)
            im = im.convert('RGB')
            img_array = np.array(im)
            faces = face_recognition.face_locations(img_array)
            if len(faces) > 0:
                face_encoding_data_service.save_person_encoding(person_id, json.dumps(faces[0].tolist()))
        train_labels, train_datas = face_encoding_data_service.list_face_encoding(open_key)
        if len(train_labels) > 0 and len(train_datas) > 0:
            train_model_service.train_model(train_labels, train_datas, get_open_key_model_path(open_key))


def get_open_key_model_path(open_key):
    return 'model/' + open_key + '_model.h5'
