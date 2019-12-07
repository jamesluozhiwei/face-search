import numpy as np
import tensorflow as tf
from face_recognition.face_recognition_cli import image_files_in_folder
from tensorflow import keras
import face_recognition as fr
import os
import os.path
import json


def train_model(train_labels, train_datas, model_path):
    dataset = tf.data.Dataset.from_tensor_slices((train_datas, train_labels))
    dataset = dataset.batch(32)
    dataset = dataset.repeat()
    output_node = len(train_labels)
    model = keras.Sequential([
        keras.layers.Dense(128, activation=tf.nn.relu),
        keras.layers.Dense(128, activation=tf.nn.relu),
        keras.layers.Dense(output_node, activation=tf.nn.softmax)
    ])
    model.compile(optimizer='adam',
                  loss='sparse_categorical_crossentropy',
                  metrics=['accuracy'])
    steps_per_epoch = 30
    if steps_per_epoch > len(train_datas):
        steps_per_epoch = len(train_datas)
    model.fit(dataset, epochs=10, steps_per_epoch=steps_per_epoch)

    model.save(model_path)


def testTrain(train_dir, model_save_path):
    """
    训练指定文件夹下的所有人的图片
    :param train_dir:
    :param model_save_path:
    :return:
    """
    # 遍历训练集中的子文件夹
    labels = []
    datas = []
    i = 0
    for user_dir in os.listdir(train_dir):
        if not os.path.isdir(os.path.join(train_dir, user_dir)):
            continue
        # 遍历这个人的每一张照片
        print(i)
        for image_path in image_files_in_folder(os.path.join(train_dir, user_dir)):
            # 加载图片
            image = fr.load_image_file(image_path)
            # 定位人脸位置
            boxes = fr.face_locations(image)
            # 获取人脸特征
            encodings = fr.face_encodings(image, known_face_locations=boxes)
            print(image_path)
            if len(encodings) == 0:
                print(image_path + "have no face")
                continue
            # 判断是否是已存在用户
            labels.append(i)
            datas.append(encodings[0].tolist())
        i = i + 1

    train_model(labels, datas, model_save_path)


def searchUnknown(unknown_path, model_path):
    model = tf.keras.models.load_model(model_path)
    face_encode = fr.face_encodings(fr.load_image_file(unknown_path))
    # print(face_encode)
    result = []
    for j in range(len(face_encode)):
        predictions1 = model.predict(np.array(face_encode[j]).reshape(1, 128))
        # print(predictions1)
        print(face_encode[j].tolist())
        print(json.dumps(face_encode[j].tolist()))
        if np.max(predictions1[0]) > 0.90:
            print(np.argmax(predictions1[0]).dtype)
            result.append(np.argmax(predictions1[0]))
    return result


def test():
    model_path = 'model/face_model_4.h5'
    # testTrain('/Users/apple/Documents/faces/face_recognition', model_path)
    result = searchUnknown('/Users/apple/Documents/faces/unknown/hg-pyy2.jpeg', model_path)
    print(result)


if __name__ == '__main__':
    test()
    # print(json.dumps(data))
