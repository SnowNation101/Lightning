<template>
    <div id="sideNav">
        <el-menu id="elm" default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
            background-color="#fff" text-color="#545c64" active-text-color="#1b3dd4">
            <el-menu-item index="1" @click="show3()">
                <i class="el-icon-menu"></i>
                <span slot="title">数据中心</span>
            </el-menu-item>
            <el-menu-item index="2" @click="show1()">
                <i class="el-icon-document"></i>
                <span slot="title">数据分析</span>
            </el-menu-item>
            <el-menu-item index="3" @click="show2()">
                <i class="el-icon-thumb"></i>
                <span slot="title">功率预测</span>
            </el-menu-item>
            <el-menu-item index="4" @click="show4()">
                <i class="el-icon-thumb"></i>
                <span slot="title">辅助决策</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
export default {
    name: 'sideNav',
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        show1() {
            this.$store.commit("show1");
        },
        show2() {
            this.$store.commit("show2");
        },
        show3() {
            this.$store.commit("show3");
        },
        show4() {
            this.$store.commit("show4");
        },
    }
}
</script>

<style>
#elm {
    overflow: hidden;
    height: 88vh;
    font-weight: bold;
    padding: 10px 2%;
}

.el-menu-item i {
    font-size: 24px;
    line-height: 24px;
}

.el-menu-item span {
    font-size: 16px;
    line-height: 24px;
}
</style>