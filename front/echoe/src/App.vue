<template>
  <v-app id="inspire">
    <v-app-bar
        class="px-3"
        flat
        density="compact"
    >

      <v-spacer></v-spacer>

      <v-tabs color="grey-darken-2" v-model="selectedTab">
        <v-tab width="200"
            v-for="tab in tabs"
            :key="tab.title"
            :value="tab.title"
            :text="tab.title"
        />
      </v-tabs>

      <v-spacer></v-spacer>

      <v-menu>
        <template v-slot:activator="{ props }">
          <v-avatar v-bind="props" class="cursor-pointer"
                    color="grey-darken-1"
                    size="32">
            <v-img :src="userImage" v-if="!!userImage"/>
          </v-avatar>
        </template>

        <v-list>
          <v-list-item
              v-for="item in items"
              @click="menuAction(item.action)"
              :key="item.title"
          >
            <v-list-item-title :href="item.link">
              {{item.title}}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>

    <v-main class="bg-grey-lighten-3">
      <v-window v-model="selectedTab">
        <v-window-item v-for="tab in tabs"
                  :key="tab.title"
                  :value="tab.title"
        >
          <keep-alive>
            <component :is="tab.component"></component>
          </keep-alive>
        </v-window-item>
      </v-window>
    </v-main>
    <v-footer class="flex-0-0">
      <v-col class="text-center" cols="12" color="grey-darken-2">
        ©{{ new Date().getFullYear() }} naoido
      </v-col>
    </v-footer>
  </v-app>
</template>


<script>
import { auth } from "@/plugins/firebaseConfig"
import { signOut } from "firebase/auth";
import LoginForm from "@/components/Login.vue"
import Profile from "@/components/Profile.vue";
import Home from "@/components/Home.vue";
import axios from "@/plugins/axios";

const items = []

export default {
  data: () => ({
    items: items,
    selectedTab: null,
    tabs: [
      {
        title: "HOME",
        component: Home

      }
    ],
    userImage: localStorage.getItem('userImage')
  }),
  async mounted() {
    if(localStorage.getItem("token")) {
      items.push({title: "Profile", action: "profile"})
      items.push({title: "logout", action: "logout"})
      this.tabs.push({title: "PROFILE", component: Profile})

      if (!localStorage.getItem("userImage") || !localStorage.getItem("userId") || !localStorage.getItem("userName")) {
        const data = (await axios.get("/api/api/v1/profile/")).data
        localStorage.setItem("userName", data.userName)
        localStorage.setItem("userId", data.userId)
        localStorage.setItem("userImage", data.image)
      }

    } else {
      items.push({title: "Login", action:"login"})

      this.tabs.push({title: "LOGIN", component: LoginForm})
    }
  },
  methods: {
    menuAction(action) {
      switch (action) {
        case "login": {
          this.selectedTab = "LOGIN"
          break;
        }
        case "profile": {
          this.selectedTab = "PROFILE"
          break;
        }
        case "logout": {
          signOut(auth).then(() => {
            localStorage.removeItem("token")
            localStorage.removeItem("userId")
            localStorage.removeItem("userName")
            localStorage.removeItem("userImage")
            window.location.reload()
          }).catch((error) => {
            console.log(error)
          })
          break;
        }
      }
    }
  }
}
</script>