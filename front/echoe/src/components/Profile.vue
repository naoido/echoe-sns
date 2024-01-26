<template>
  <v-card :width="300" class="mx-auto mt-10 pb-10">
    <v-card-title>
      {{userName}}
    </v-card-title>
    <v-card-text>
      {{userId}}
    </v-card-text>
    <v-btn @click="logout" class="mt-4 mx-4" color="red">Logout</v-btn>
  </v-card>
</template>

<script>
  import {signOut} from "firebase/auth";
  import {auth} from "@/plugins/firebaseConfig";

  export default {
    name: "profile-component",
    data: () => ({
      userId: localStorage.getItem("userId"),
      userName: localStorage.getItem("userName")
    }),
    methods: {
      logout() {
        signOut(auth).then(() => {
          localStorage.removeItem("token")
          localStorage.removeItem("userId")
          localStorage.removeItem("userName")
          localStorage.removeItem("userImage")
          window.location.reload()
        }).catch((error) => {
          console.log(error)
        })
      }
    }
  }
</script>