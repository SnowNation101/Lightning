# 导入pymsql模块
import numpy as np
import pymysql

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

        # while True:
        #sql = input('输入SQL语句:') #通过这里进行链接
        #print('Received Message: ', sql)  # 这里发过来的data需要是sql语言，直接用于操作数据库
        cur.execute(sql)
        self.conn.commit()
        results = cur.fetchall()
        # if len(results) != 0:
        #     print('Returned Data:')
        #     for i in results:
        #         print(str(i))
        # yn = input('按N断开连接,任意键继续：').strip()
        # if yn == 'N':
        #     break

        cur.close()
        self.close_mysql()
        return results


if __name__ == "__main__":
    # 定义变量
    username = 'root'
    host = '127.0.0.1'
    passwd = '123456'
    database = 'lightning'

    # 使用try--except
    try:
        # 创建TestMysql的实例
        mysql = TestMysql(username, host, passwd, database)
        mysql.conn_mysql()
        # sql = ""
        # mysql.get_data(sql)
        # QApplication.processEvents()
    except pymysql.err.ProgrammingError as e:
        print("Exception Error is %s" % (e))
    except pymysql.err.OperationalError as e:
        print("Exception Error is %s" % (e))

    #获取预测的数据
    sql = "select light_intensity,temperature,wind_speed,humidity from weather_data;"
    x_test = mysql.get_data(sql)
    sql = "select light_intensity from weather_data;"
    light = mysql.get_data(sql)
    sql = "select time from weather_data;"
    date_result = mysql.get_data(sql)

    x_test = np.array(x_test)
    num = 500  # 用多少天训练样本预测下一天的气象
    real_X = []
    for i in range(x_test.shape[0] - num):
        t = x_test[i:i + num]
        t = t.reshape(t.shape[0] * t.shape[1])
        real_X.append(t)
    x_test = np.array(real_X)

    # 保存该模型-joblib
    import joblib
    # 载入模型
    reload_model = joblib.load('model.pkl')
    # 利用载入好的模型进行预测
    predicted = reload_model.predict(x_test)

    sql = "select max(id) from generation_power;"
    max_id = mysql.get_data(sql)
    max_id = max_id[0][0]

    print(x_test.shape[0])
    for i in range(x_test.shape[0] + 1 - 96,x_test.shape[0] + 1):
        if int(light[i-1][0]) == 0 or int(light[i-1][0]) == 1:
            text = str(abs(predicted[i-1]*0.5))
            # text = '0'
        else:
            text = str(abs(predicted[i-1]))

        sql = "select * from generation_power where time = \'" + date_result[i+num-1][0].strftime("%Y/%m/%d %H:%M") + "\';"
        # print(sql)
        result = mysql.get_data(sql)
        if len(result) != 0:
            sql = "update generation_power set predict_val = " + str(text) + " where time = \'" + date_result[i+num-1][0].strftime("%Y/%m/%d %H:%M") + "\';"
            # print(sql)
        else:
            max_id += 1
            sql = "insert into generation_power value(" + str(max_id) + ", " + text + ", null, 01, \'" + date_result[i+num-1][0].strftime("%Y/%m/%d %H:%M") + "\');"
        mysql.get_data(sql)


