<template>
    <v-card outlined>
        <v-card-title>
            Issurance # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="발급파일일련번호" v-model="item.발급파일일련번호" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="신청번호" v-model="item.신청번호" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="파일Id" v-model="item.파일Id" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="파일명" v-model="item.파일명" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="파일경로명" v-model="item.파일경로명" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="파일확장자명" v-model="item.파일확장자명" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="파일크기" v-model="item.파일크기" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="파일순서" v-model="item.파일순서" :editMode="editMode" @change="change" />
            </div>
            <FileType offline label="FileType" v-model="item.fileType" :editMode="false" :key="false" @change="change" />
            <div>
                <String label="Status" v-model="item.status" :editMode="editMode" @change="change" />
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
        name: 'IssueIssuranceDetail',
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
            var temp = await axios.get(axios.fixUrl('/issurances/' + params.id))
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
