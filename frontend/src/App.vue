<template>
  <div class="container">

    <div class="jumbotron jumbotron-fluid">
      <div class="container">
        <h1 class="display-6">Account Transfer Schedule Application</h1>
        <p class="lead">In this project, VueJS is used for frontend, Java Spring Boot for backend side.</p>
      </div>
    </div>

    <ul class="nav nav-tabs">
      <li class="nav-item">
        <router-link to="/" class="nav-link" active-class="active" exact>Home</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/create" class="nav-link" active-class="active">Create</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/balance" class="nav-link" active-class="active">Balance</router-link>
      </li>
    </ul>

    <router-view></router-view>

  </div>
</template>

<script>

export default {
  name: 'app'
  
}
</script>

<style>
.error {
  color: red;
}

.error-input {
  border-color: red;
  color: red;
}
</style>
