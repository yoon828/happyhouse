<template>
  <b-container class="flex-direction-col">{{ deal.floor }} </b-container>
</template>

<script>
export default {
  data() {
    return {
      //해당 아파트의 거래 내역을 조회
      deals: [],
      fields: ["거래일", "면적", "금액", "층수"],
    };
  },
  props: {
    deal: Object,
  },
};
</script>

<style></style>
