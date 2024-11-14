<template>
    <v-card outlined>
        <v-card-title>
            Integration # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="연계Id" v-model="item.연계Id" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="신청번호" v-model="item.신청번호" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="서비스Id" v-model="item.서비스Id" :editMode="editMode" @change="change" />
            </div>
            <유형일렬번호 offline label="유형일렬번호" v-model="item.유형일렬번호" :editMode="false" :key="false" @change="change" />
            <div>
                <String label="연계인터페이스" v-model="item.연계인터페이스" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="송신데이터" v-model="item.송신데이터" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="수신데이터" v-model="item.수신데이터" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="결과코드" v-model="item.결과코드" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="결과메시지" v-model="item.결과메시지" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="송신일시" v-model="item.송신일시" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="수신일시" v-model="item.수신일시" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="등록일시" v-model="item.등록일시" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="수정일시" v-model="item.수정일시" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple lighten-2" large @click="goList">List</v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode"
            >
                Cancel
            </v-btn>
        </v-card-actions>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'IntegrationIntegrationDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
            editMode: false,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/integrations/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
            goList() {
                var path = window.location.href.slice(window.location.href.indexOf("/#/"), window.location.href.lastIndexOf("/#"));
                path = path.replace("/#/", "/");
                this.$router.push(path);
            },
            edit() {
                this.editMode = true;
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.item._links.self.href))
                    }

                    this.editMode = false;

                    this.$emit('input', this.item);
                    this.$emit('delete', this.item);

                } catch(e) {
                    console.log(e)
                }
            },
        },
    };
</script>
