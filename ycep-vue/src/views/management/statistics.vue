<template>
  <!--  顶部导航栏-->
  <Top/>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Top from "../../components/top.vue";

export default defineComponent({
  name: "statistics",
  data() {
    return {
      data: "",
    };
  },
  components: {Top},
  mounted() {},
  methods: {
    ready() {},
  },
});
</script>

<style lang="scss" scoped></style>
