<template>
    <div id="topNav">
        <div id="icon">
            <i class="el-icon-lightning"></i>
        </div>
        <div id="text">
            <i>Lightning</i>
        </div>
    </div>
</template>

<script>
export default {
    name: 'topNav',
}
</script>

<style>
#topNav {
    width: 100%;
    height: 100%;
    background-color: white;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
    display: flex;
}

#icon {
    color: rgb(27, 61, 212);
    font-size: 2em;
    padding: 15px 0;
    padding-left: 20px;
}

#text {
    color: rgb(27, 61, 212);
    font-size: 24px;
    padding: 18px 0;
    padding-left: 10px;
}
</style>