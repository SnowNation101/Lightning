<template>
    <div id="decisionNode">
        <div id="decision">
            <div class="head">
                <p
                    style="margin-top: 0;margin-bottom: 0;padding: 0;font-family: Arial, Helvetica, sans-serif;font-size: 20px;">
                    辅助市场交易</p>
                <p
                    style="margin-top: 0;margin-bottom: 0;padding: 0;font-family: Arial, Helvetica, sans-serif;font-size: 10px;">
                    Assist in electricity market trading</p>
            </div>
            <div class="body">
                <div id="st">
                    <img id="static" src="../../assets/assist.png" />
                </div>
                <div>
                    <p style="margin-top: 10%;margin-bottom: 0;padding: 0;font-family: Arial, Helvetica, sans-serif;font-size: 20px;">我国光伏发电量增长迅速，市场前景较好。</p>
                </div>
            </div>
        </div>
        <div id="fault">
            <div class="head">
                <p
                    style="margin-top: 0;margin-bottom: 0;padding: 0;font-family: Arial, Helvetica, sans-serif;font-size: 20px;">
                    异常情况分析</p>
                <p
                    style="margin-top: 0;margin-bottom: 0;padding: 0;font-family: Arial, Helvetica, sans-serif;font-size: 10px;">
                    Abnormal situation analysis</p>
            </div>
            <div class="body">
                <div id="slct">
                    <p v-if='!this.$store.state.errorState' style="font-family: Arial, Helvetica, sans-serif;font-size: 15px;">
                        数据无异常！常见的异常如下，请注意防范。</p>
                    <p v-if='this.$store.state.errorState' style="font-family: Arial, Helvetica, sans-serif;font-size: 15px;">
                        误差较大！可能有如下异常，请及时检查。</p>
                    <el-radio-group v-model="errorType">
                        <el-radio-button :label="1">发电板损坏</el-radio-button>
                        <el-radio-button :label="2">积雪覆盖</el-radio-button>
                        <el-radio-button :label="3">发电板污浊</el-radio-button>
                    </el-radio-group>
                </div>
                <div id="info">
                    <div id="img">
                        <img id="errorImg" :src="url" />
                    </div>
                    <div id="message">
                        <p style="font-family: Arial, Helvetica, sans-serif;font-size: 20px;">分析:</p>
                        <p id="errorMessage1" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;">
                            可能发电板由于意外因素损坏，包括电池片开裂、弯曲，焊带与电池片脱层、偏移，EVA脱层、气泡、绝缘小条黄变，镀膜玻璃表层脱落，硅胶气泡，光伏组件边框变形等……
                        </p>
                        <p style="font-family: Arial, Helvetica, sans-serif;font-size: 20px; ">建议:</p>
                        <p id="errorMessage2" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;">
                            有的是可以修复的，有的是不可逆。若电池、焊带、EVA、背板损坏就需要先把组件边框拆下来，隔离背板，然后通过高温加热将电池片拆卸下来，然后再焊接恢复。
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'decisionNode',
    data() {
        return {
            errorType: '1',
            url: '',
        }
    },
    mounted() {
        this.url = require("../../assets/broke.png");
        this.$store.commit('getErrorState');
    },
    watch: {
        errorType: {
            handler() {
                var x = document.getElementById("errorMessage1");
                var y = document.getElementById("errorMessage2");
                switch (this.errorType) {
                    case 1: {
                        this.url = require("../../assets/broke.png");
                        x.innerHTML = "可能发电板由于意外因素损坏，包括电池片开裂、弯曲，焊带与电池片脱层、偏移，EVA脱层、气泡、绝缘小条黄变，镀膜玻璃表层脱落，硅胶气泡，光伏组件边框变形等……"
                        y.innerHTML = "有的是可以修复的，有的是不可逆。若电池、焊带、EVA、背板损坏就需要先把组件边框拆下来，隔离背板，然后通过高温加热将电池片拆卸下来，然后再焊接恢复。"
                        break;
                    }
                    case 2: {
                        this.url = require("../../assets/snow.png");
                        x.innerHTML = "雪落在组件上会导致组件的阴影遮挡，造成组件发电量的缺失."
                        y.innerHTML = "对于寒冷地区经常下雪的地区，建议安装组件的倾角大一些，积雪到一定程度会滑落，减少了雪的累积速度。安装时组件底部和地面保持一定的距离，雪滑落堆积在底部，不至于后来堆积到组件。不要等积雪过厚再清理，以免组件积雪过度结冰。组件堆积有厚重积雪可以利用柔软物品将雪推下，注意不要划伤玻璃，不宜使用热水浇开冰层，以免冷热不均使玻璃开裂。清扫时不宜脚踩组件，以免发生组件隐裂。宜使用柔软布制拖把，轻扫积雪。"
                        break;
                    }
                    case 3: {
                        this.url = require("../../assets/dust.png");
                        x.innerHTML = "光伏组件表面污浊对其发电效率的影响相当显著。其一，表面的污浊影响光线的透射率，进而影响到组件表面所接受的辐射量；其二，污浊物粘附在电池板表面会形成阴影，在光伏组件局部产生热斑效应，进而对光伏板造成损伤。"
                        y.innerHTML = "高压水清洗或者电动光伏清洗机清洗除尘时间选择一般选择清晨或者傍晚，因为清晨或者傍晚阳光较弱，电量损失小。避免强光下人为阴影使光伏阵列发生热斑效应。清洗周期一般根据组件的污染程度选择清洗周期，一般每月清洗1~2次，灰尘较多的地方可酌情增加次数。"
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
    }
}
</script>

<style>
#st{
    width: 100%;
    height: 60%;
}
#static{
    width: 100%;
    height: 100%;
    object-fit: contain;
}
#decisionNode {
    width: 100%;
    height: 86vh;
}

#decision {
    float: left;
    width: 49%;
    height: 100%;
    margin-right: 1%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}

.head {
    width: 100%;
    height: 10%;
    text-align: center;
}

.body {
    width: 100%;
    height: 90%;
    text-align: center;
}

#fault {
    float: left;
    width: 49%;
    height: 100%;
    margin-left: 1%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}

#slct {
    width: 100%;
    height: 15%;
}

#info {
    width: 100%;
    height: 85%;
}

#errorImg {
    width: 100%;
    height: 100%;
    object-fit: contain;
}

#img {
    width: 100%;
    height: 40%;
}

#message {
    width: 80%;
    height: 60%;
    text-align: left;
    margin-left: 10%;
}
</style>