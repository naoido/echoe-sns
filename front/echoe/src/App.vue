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
                    size="32"/>
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
import LoginForm from "@/components/Login.vue";
import HelloWorld from "@/components/HelloWorld.vue";

const items = []

export default {
  data: () => ({
    items: items,
    selectedTab: null,
    tabs: [
      {
        title: "HOME",
        component: HelloWorld

      }
    ]
  }),
  mounted() {
    if(localStorage.getItem("token")) {
      items.push({title: "Profile", action: "profile"})
      items.push({title: "SignOut", action: "signOut"})

      this.tabs.push({title: "PROFILE", component: LoginForm})
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
          console.log("profile")
          break;
        }
        case "signOut": {
          signOut(auth).then(() => {
            localStorage.removeItem("token")
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