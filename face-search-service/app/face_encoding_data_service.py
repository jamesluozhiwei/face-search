import numpy as np
import json
import pymysql

db_host = 'localhost'
db_user = 'root'
db_password = '123456'
db_name = 'db_face_search_service'


def list_face_encoding(open_key):
    # db = self_db(host=db_host, user=db_user, passwd=db_password, db=db_name)
    conn = pymysql.connect(host=db_host, port=3306, user=db_user, password=db_password, database=db_name, charset='utf8')
    # 获得Cursor对象
    sql = "select \
        tb_user_person.id, \
        tb_person_data.face_encoding \
    from \
        tb_user_person \
        inner join tb_person_data on tb_person_data.person_id = tb_user_person.id \
    where \
        tb_user_person.open_key = %s "
    cursor = conn.cursor()
    params = [open_key]
    cursor.execute(sql, params)
    # 获取结果集
    labels = []
    data = []
    results = cursor.fetchall()
    for result in results:
        labels.append(result[0])
        data.append(json.loads(result[1]))
    # db.__exit__()
    cursor.close()
    conn.close()
    print('labels')
    print(labels)
    print(data)
    return np.array(labels), np.array(data)


def save_person_encoding(person_id, face_encoding):
    # db = self_db(host=db_host, user=db_user, passwd=db_password, db=db_name)
    conn = pymysql.connect(host=db_host, port=3306, user=db_user, password=db_password, database=db_name,
                           charset='utf8')
    print('face encoding: ')
    print(face_encoding)
    #  % (person_id, face_encoding)
    sql = "insert into tb_person_data(person_id,face_encoding) values(%s,%s)"
    cursor = conn.cursor()
    print('sql: ')
    print(sql)
    params = [person_id, face_encoding]
    cursor.execute(sql, params)
    conn.commit()
    cursor.close()
    conn.close()
