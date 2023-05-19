# 导入pymsql模块
import numpy as np
import pymysql
import datetime
import sys

# 创建连接MYSQL的类
class TestMysql:
    # 初始化变量
    def __init__(self, username, host, passwd, database):
        self.username = username
        self.host = host
        self.passwd = passwd
        self.database = database

    # 创建数据库连接
    def conn_mysql(self):
        conn = pymysql.connect(user=self.username, host=self.host, password=self.passwd, db=self.database)
        return conn

    # 关闭数据库的提示信息
    def close_mysql(self):
        # print("MySQL is Closed")
        pass

    # 查询数据
    def get_data(self,sql):
        self.conn = self.conn_mysql()
        cur = self.conn.cursor()
        cur.execute(sql)
        self.conn.commit()
        results = cur.fetchall()
        cur.close()
        self.close_mysql()
        return results


if __name__ == "__main__":
    # 定义变量
    username = 'root'
    host = 'localhost'
    passwd = '123456'
    database = 'lightning-copy'

    # 使用try--except
    try:
        # 创建TestMysql的实例
        mysql = TestMysql(username, host, passwd, database)
        mysql.conn_mysql()
    except pymysql.err.ProgrammingError as e:
        print("Exception Error is %s" % (e))
    except pymysql.err.OperationalError as e:
        print("Exception Error is %s" % (e))

    #获取预测的数据

    sql = "select max(id) from weather_data;"
    table_len = mysql.get_data(sql)
    table_len = table_len[0][0]

    h = float(sys.argv[1])
    li = float(sys.argv[2])
    tem = float(sys.argv[3])
    ws = float(sys.argv[4])

    now_time = datetime.datetime.now().strftime('%Y-%m-%d  %H:%M:%S')
    sql = "insert into weather_data values (" + str(table_len+1) + "," + str(h) + "," + str(li) + "," + str(tem) + ", \'" + str(now_time) + "\'," + str(ws) + ");"
    mysql.get_data(sql)

    max_id = table_len + 1
    sql = "select light_intensity,temperature,wind_speed,humidity from weather_data where id >= " + str(max_id-500+1) + " and id <= " + str(max_id) + ";"
    x_test = mysql.get_data(sql)
    x_test = np.array(x_test)
    x_test = x_test.reshape(1,2000)

    # 保存该模型-joblib
    import joblib
    # 载入模型
    reload_model = joblib.load('D:/dev/lightning/backend/src/main/py_new/model.pkl')
    # 利用载入好的模型进行预测
    predicted = reload_model.predict(x_test)

    print(predicted[0])

    # table_len = 3168
    sql = "delete from weather_data where id >" + str(table_len) + ";"
    mysql.get_data(sql)