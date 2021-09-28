<template>
<div>
   <div id="main">
            <div class="p-fluid p-formgrid p-grid">
                <div class="p-field p-col-7">
                   <img :src="require(`@/assets/images/left.png`)"/>
                </div>
                <div class="p-field p-col-5">
                   <h1>뭘 넣을까요</h1>
                </div>
            </div>
        </div>
</div>
</template>

<script>
export default {
  props: {
    msg: String,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.row div{
  display: inline;
}

#main {
  margin-top: 5%;
}

#main img {
  margin-left: 30%;
  margin-top : 5%;
  width: 50%;
}

</style>
