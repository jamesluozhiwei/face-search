import numpy as np
import json
import DB

db_host = 'localhost'
db_user = 'root'
db_password = '123456'
db_name = 'db_face_search_service'

def list_face_encoding(open_key):
    db = DB(host=db_host, user=db_user, passwd=db_password, db=db_name)
    sql = "select \
        tb_user_person.person_tag, \
        tb_person_data.face_encoding \
    from \
        tb_user_person \
        inner join tb_person_data on tb_person_data.person_id = tb_user_person.id \
    where \
        tb_user_person.open_key = %s " % open_key
    cursor = db.cur
    cursor.execute(sql)
    # 获取结果集
    labels = []
    data = []
    results = cursor.fetchall()
    for result in results:
        labels.append(result[0])
        data.append(json.loads(result[1]))
    db.__exit__()
    return np.array(labels), np.array(data)

def save_person_encoding(person_id, face_encoding):
    db = DB(host=db_host, user=db_user, passwd=db_password, db=db_name)
    sql = "insert into tb_person_data(person_id,face_encoding) values(%s,%s)" % (person_id, face_encoding)
    cursor = db.cur
    cursor.execute(sql)
    db.__exit__()
