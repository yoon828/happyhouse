<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>로그인</h2>
      <p slot="description" class="description">
        happy house에 오신 걸 환영 합니다.
      </p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>face</md-icon>
        <label>아이디</label>
        <md-input v-model="userid"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>lock_outline</md-icon>
        <label>비밀번호</label>
        <md-input type="password" v-model="userpw"></md-input>
      </md-field>
      <md-button slot="footer" class="md-success">
        로그인
      </md-button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
#box {
  padding: 100px;
  margin: 5px;
}
.md-form-group {
  width: 50%;
  margin: 0 auto;
  padding: 15px;
}
</style>
