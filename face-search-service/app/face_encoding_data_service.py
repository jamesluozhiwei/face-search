import numpy as np
import json
import DB


def list_face_encoding(open_key):
    db = DB(host='localhost', user='root', passwd='123456', db='db_face_search_service')
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
